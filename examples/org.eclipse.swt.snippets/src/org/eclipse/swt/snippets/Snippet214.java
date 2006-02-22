package org.eclipse.swt.snippets;
/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/*
 * Control example snippet: set a background image
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.2
 */
public class Snippet214 {
	static Image oldImage;
	public static void main(String [] args) {
		final Display display = new Display ();
		final Shell shell = new Shell (display);
		shell.setBackgroundMode (SWT.INHERIT_DEFAULT);
		FillLayout layout1 = new FillLayout (SWT.VERTICAL);
		layout1.marginWidth = layout1.marginHeight = 10;
		shell.setLayout (layout1);
		Group group = new Group (shell, SWT.NONE);
		group.setText ("Group ");
		RowLayout layout2 = new RowLayout (SWT.VERTICAL);
		layout2.marginWidth = layout2.marginHeight = layout2.spacing = 10;
		group.setLayout (layout2);
		for (int i=0; i<8; i++) {
			Button button = new Button (group, SWT.RADIO);
			button.setText ("Button " + i);
		}
		shell.addListener (SWT.Resize, new Listener () {
			public void handleEvent (Event event) {
				Rectangle rect = shell.getClientArea ();
				Image newImage = new Image (display, Math.max (1, rect.width), 1);	
				GC gc = new GC (newImage);
				gc.setForeground (display.getSystemColor (SWT.COLOR_WHITE));
				gc.setBackground (display.getSystemColor (SWT.COLOR_BLUE));
				gc.fillGradientRectangle (rect.x, rect.y, rect.width, 1, false);
				gc.dispose ();
				shell.setBackgroundImage (newImage);
				if (oldImage != null) oldImage.dispose ();
				oldImage = newImage;
			}
		});
		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		if (oldImage != null) oldImage.dispose ();
		display.dispose ();
	}
}
