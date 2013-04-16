package org.eclipse.swt.widgets;

import java.io.UnsupportedEncodingException;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gtk.*;

public class UbuntuTrayItem extends Widget {
	Tray parent;
	long /*int*/ appIndicatorHandle;
	// We could cache the icon path and current icon, but meh

	public static int PASSIVE = OS.APP_INDICATOR_STATUS_PASSIVE;
	public static int ACTIVE = OS.APP_INDICATOR_STATUS_ACTIVE;
	public static int ATTENTION = OS.APP_INDICATOR_STATUS_ATTENTION;

	public static boolean supported()
	{
		return OS.app_indicator_supported();
	}

	private static byte[] asUtf8CString(String s) {
		try {
			byte[] needsterminator = s.getBytes("UTF-8");
			byte[] retval = new byte[needsterminator.length + 1];
			System.arraycopy(needsterminator, 0, retval, 0, needsterminator.length);
			retval[needsterminator.length] = 0;
			return retval;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public UbuntuTrayItem (Tray parent, int style, String appId) {
		super (parent, style);
		this.parent = parent;
		createWidget (parent.getItemCount());
		if (!OS.app_indicator_supported()) {
			throw new RuntimeException("No libappindicator support found");
		}
		System.err.println("UbuntuTrayItem: we have appindicator support");
		byte[] appIdutf = asUtf8CString(appId);
		byte[] emptystring = new byte[1];
		emptystring[0] = 0;
		this.appIndicatorHandle = OS.app_indicator_new(appIdutf, emptystring);
	}

	public void setIconPath(String path)
	{
		System.err.println("UbuntuTrayItem: setIconPath(" + path + ")");
		byte[] pathUtf = asUtf8CString(path);
		OS.app_indicator_set_icon_theme_path(appIndicatorHandle, pathUtf);
	}

	/**
	 * Linux uses the freedesktop.org icon naming standard.  This means that
	 * we can't set an Icon *object*, but can simply specify a name which lets
	 * the tray icon provider find the appropriate icon for the current
	 * resolution/colorscheme/etc.
	 * @param iconName The icon name under the fd.o Icon Theme Specification
	 * @param description A textual description of the icon for accessibility
	 */
	public void setIcon(String iconName, String description)
	{
		System.err.println("UbuntuTrayItem: setIcon(" + iconName + ", " + description + ")");
		byte[] iconNameUtf = asUtf8CString(iconName);
		byte[] descriptionUtf = asUtf8CString(description);
		OS.app_indicator_set_icon_full(appIndicatorHandle, iconNameUtf, descriptionUtf);
	}

	public void setAttentionIcon(String iconName, String description)
	{
		System.err.println("UbuntuTrayItem: setAttentionIcon(" + iconName + ", " + description + ")");
		byte[] iconNameUtf = asUtf8CString(iconName);
		byte[] descriptionUtf = asUtf8CString(description);
		OS.app_indicator_set_attention_icon_full(appIndicatorHandle, iconNameUtf, descriptionUtf);
	}

	public void setMenu(Menu menu)
	{
		System.err.println("UbuntuTrayItem: setMenu(" + menu + ")");
		if (menu != null) {
			System.err.println("menu.handle = " + menu.handle);
			OS.app_indicator_set_menu(appIndicatorHandle, menu.handle);
		}
	}

	public void setStatus(int status)
	{
		System.err.println("UbuntuTrayItem: setStatus(" + status + ")");
		OS.app_indicator_set_status(appIndicatorHandle, status);
	}
}
