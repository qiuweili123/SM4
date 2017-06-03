package com.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class TestTableAndtab extends AbstractExample {
    private Table table;
    private Tree tree;

    public static void main(String[] args) {
        new TestTableAndtab().run();
    }

    public void todo(Shell shell) {
        TabFolder tabFolder = new TabFolder(shell, SWT.BORDER);

        TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
        tabItem1.setText("第一页");

        Composite compsoite1 = new Composite(tabFolder, SWT.NONE);
        tabItem1.setControl(compsoite1);

        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        compsoite1.setLayout(layout);
        Group treeGroup = new Group(compsoite1, SWT.NONE);
        treeGroup.setText("Tree");
        GridData griddata = new GridData(GridData.FILL_BOTH);
        griddata.heightHint = 50;
        treeGroup.setLayoutData(griddata);
        treeGroup.setLayout(new GridLayout(1, false));
        {
            tree = new Tree(treeGroup, SWT.SINGLE);
            tree.setLayoutData(new GridData(GridData.FILL_BOTH));

            TreeItem stu1 = new TreeItem(tree, SWT.NONE);
            stu1.setText("xingoo");
            {
                TreeItem info1 = new TreeItem(stu1, SWT.NONE);
                info1.setText("age:25");

                TreeItem info2 = new TreeItem(stu1, SWT.NONE);
                info2.setText("tel:12345");
            }
            TreeItem stu2 = new TreeItem(tree, SWT.NONE);
            stu2.setText("halo");
            {
                TreeItem info3 = new TreeItem(stu2, SWT.NONE);
                info3.setText("age:25");

                TreeItem info4 = new TreeItem(stu2, SWT.NONE);
                info4.setText("tel:67890");
            }

            tree.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent evt) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{tree.getSelection()[0].toString(), tree.getSelection()[0].getText()});
                }
            });
        }
        Group tableGroup = new Group(compsoite1, SWT.NONE);
        tableGroup.setText("Table");
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = 20;
        tableGroup.setLayoutData(gd);
        tableGroup.setLayout(new GridLayout(1, false));
        {    //创建一个单选的，有边界的，一行全选的表格
            table = new Table(tableGroup, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
            table.setHeaderVisible(true);//设置表头可见
            table.setLinesVisible(true);//设置线条可见
            table.setLayoutData(new GridData(GridData.FILL_BOTH));

            TableColumn column1 = new TableColumn(table, SWT.NULL);
            column1.setText("Tree Item");
            column1.pack();
            column1.setWidth(150);

            TableColumn column2 = new TableColumn(table, SWT.NULL);
            column2.setText("Parent");
            column2.pack();
            column2.setWidth(150);
        }


        TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
        tabItem2.setText("第二页");
    }
}