package com.eclipse.swt;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractExample {
    public void run() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("shell example");
        shell.setBounds(200, 200, 400, 280);
        shell.setLayout(new FillLayout());
        todo(shell);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        //dispose the resource
        display.beep();
        display.dispose();
    }

    public abstract void todo(Shell shell);
}