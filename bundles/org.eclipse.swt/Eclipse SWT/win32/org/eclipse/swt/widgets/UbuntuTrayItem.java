package org.eclipse.swt.widgets;

import java.io.UnsupportedEncodingException;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;

public class UbuntuTrayItem extends Widget {
	Tray parent;
	long /*int*/ appIndicatorHandle;

	public static int PASSIVE = 0;
	public static int ACTIVE = 1;
	public static int ATTENTION = 2;
	public static boolean supported()
	{
		// stub
		return false;
	}

	public UbuntuTrayItem (Tray parent, int style, String appId) {
		// stub
		throw new RuntimeException("No libappindicator support found");
	}

	public void setIconPath(String path)
	{
		// stub
	}

	public void setIcon(String iconName, String description)
	{
		// stub
	}

	public void setMenu(Menu menu)
	{
		// stub
	}

	public void setStatus(int status)
	{
		// stub
	}
}
