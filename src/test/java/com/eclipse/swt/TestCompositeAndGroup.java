package com.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class TestCompositeAndGroup extends AbstractExample {
    private Label infoLabel;
    private Text usernameText;
    private Text passwordText;
    private Combo roleCombo;

    public static void main(String[] args) {
        new TestCompositeAndGroup().run();
    }

    public void todo(Shell shell) {
        Group testGroup = new Group(shell, SWT.NONE);
        testGroup.setText("User Login");
        //创建了一个组控件，并且使用了网格布局，设置每行有两列。并且设置了组内填充边界
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 3;
        layout.marginHeight = 10;
        testGroup.setLayout(layout);
        testGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        //创建了一个组合对象，使他占有了两个列元素。并且设置组内为两列的网格布局
        Composite composite = new Composite(testGroup, SWT.NONE);
        GridLayout layoutComposite = new GridLayout();
        layoutComposite.numColumns = 2;
        layoutComposite.marginHeight = 1;
        composite.setLayout(layoutComposite);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));

        infoLabel = new Label(composite, SWT.BORDER);
        infoLabel.setText("请输入用户名 密码");
        infoLabel.setLayoutData(new GridData(GridData.FILL_BOTH));
        infoLabel.setAlignment(SWT.LEFT);


        Label usernameLabel = new Label(testGroup, SWT.NONE);
        usernameLabel.setText("username:");

        usernameText = new Text(testGroup, SWT.BORDER);
        usernameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));


        Label passwordLabel = new Label(testGroup, SWT.NONE);
        passwordLabel.setText("password:");

        passwordText = new Text(testGroup, SWT.BORDER | SWT.PASSWORD);
        passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label roleLabel = new Label(testGroup, SWT.NONE);
        roleLabel.setText("role:");

        roleCombo = new Combo(testGroup, SWT.DROP_DOWN);
        roleCombo.setItems(new String[]{"Admin", "custom"});
        roleCombo.select(1);

        new Label(testGroup, SWT.NONE);

        Button rememberPWBtn = new Button(testGroup, SWT.CHECK);
        rememberPWBtn.setText("记住密码");

        new Label(testGroup, SWT.NONE);

        Button autoLoginBtn = new Button(testGroup, SWT.CHECK);
        autoLoginBtn.setText("自动登录");
        new Label(testGroup, SWT.NONE);

        Button loginBtn = new Button(testGroup, SWT.PUSH);
        loginBtn.setText("登录");

        loginBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                infoLabel.setText("登陆成功");

                usernameText.setText("");
                usernameText.setEnabled(false);

                passwordText.setText("");
                passwordText.setEnabled(false);

                roleCombo.setEnabled(false);
            }
        });

    }
}
