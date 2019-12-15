/*
 * Created by JFormDesigner on Thu Dec 12 18:13:12 CET 2019
 */

package henkslot;

import javafx.scene.control.SelectionModel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

/**
 * @author Gerrit
 */
public class StartScreen extends JDialog {

    public boolean only_cadava_berries = false;
    public boolean only_redberries = false;
    public boolean all_berries = false;
    public boolean use_examine_random_objects = false;
    public boolean use_say_random_words = false;
    public boolean use_turn_screen_random = false;
    public boolean use_sleep_random = false;
    Checkbox cb = new Checkbox("Enable", false);
    Object[] columnNames = {"Anti-ban method", "Enable"};
    Object[][] data = {
            {"Method: Examine random objects", true},
            {"Method: Say random words", true},
            {"Method: Turn screen random", true},
            {"Method: Sleep random", true}
    };
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        @Override
        public Class getColumnClass(int column)
        {
            for (int row = 0; row < getRowCount(); row++)
            {
                Object o = getValueAt(row, column);

                if (o != null)
                {
                    return o.getClass();
                }
            }

            return Object.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }
    };

    public StartScreen() {
        initComponents();
        setModal(true);
        table1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    if (row == 0) {
                        use_examine_random_objects = (boolean)table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 1) {
                        use_say_random_words = (boolean)table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 2) {
                        use_turn_screen_random = (boolean)table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 3) {
                        use_sleep_random = (boolean)table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }
                }
            }
        });

//        table1.setModel(model);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        // collecting all the data from the JFrame
        only_cadava_berries = (radioButton3.isSelected()) ? true : false;
        only_redberries = (radioButton4.isSelected()) ? true : false;
        all_berries = (radioButton5.isSelected()) ? true : false;
        use_examine_random_objects = (boolean)table1.getModel().getValueAt(0, 1);
        use_say_random_words = (boolean)table1.getModel().getValueAt(1, 1);
        use_turn_screen_random = (boolean)table1.getModel().getValueAt(2, 1);
        use_sleep_random = (boolean)table1.getModel().getValueAt(3, 1);

        System.out.println(use_examine_random_objects);
        System.out.println(use_say_random_words);
        System.out.println(use_turn_screen_random);
        System.out.println(use_sleep_random);
        JOptionPane.showMessageDialog(this, "Thanks for using Picker by henkslot :)", "Picker", JOptionPane.INFORMATION_MESSAGE);

        // closing the JFrame
        dispose();
    }

    public boolean isOnly_cadava_berries() {
        return only_cadava_berries;
    }

    public boolean isOnly_redberries() {
        return only_redberries;
    }

    public boolean isAll_berries() {
        return all_berries;
    }

    public boolean isUse_examine_random_objects() {
        return use_examine_random_objects;
    }

    public boolean isUse_say_random_words() {
        return use_say_random_words;
    }

    public boolean isUse_turn_screen_random() {
        return use_turn_screen_random;
    }

    public boolean isUse_sleep_random() {
        return use_sleep_random;
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Gerrit
        dialogPane = new JPanel();
        okButton = new JButton();
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        radioButton5 = new JRadioButton();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        list1 = new JList<>();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox4 = new JCheckBox();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Picker by henkslot");
        setMinimumSize(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.
            red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(null);

            //---- okButton ----
            okButton.setText("OK");
            okButton.addActionListener(e -> {
			okButtonActionPerformed(e);
			okButtonActionPerformed(e);
		});
            dialogPane.add(okButton);
            okButton.setBounds(285, 230, 85, okButton.getPreferredSize().height);

            //======== tabbedPane1 ========
            {

                //======== panel1 ========
                {

                    //---- radioButton3 ----
                    radioButton3.setText("Pick cadava berries");

                    //---- radioButton4 ----
                    radioButton4.setText("Pick redberries");

                    //---- radioButton5 ----
                    radioButton5.setText("Pick both");
                    radioButton5.setSelected(true);

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(radioButton3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioButton4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioButton5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)))
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(radioButton3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButton4, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioButton5, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                    );
                }
                tabbedPane1.addTab("Picker options", panel1);

                //======== panel2 ========
                {

                    //======== scrollPane2 ========
                    {

                        //---- list1 ----
                        list1.setModel(new AbstractListModel<String>() {
                            String[] values = {
                                "Method: Examine random objects",
                                "Method: Say random words",
                                "Method: Turn screen random",
                                "Method: Sleep random"
                            };
                            @Override
                            public int getSize() { return values.length; }
                            @Override
                            public String getElementAt(int i) { return values[i]; }
                        });
                        list1.setFixedCellHeight(25);
                        scrollPane2.setViewportView(list1);
                    }

                    //---- checkBox1 ----
                    checkBox1.setText("Enable");

                    //---- checkBox2 ----
                    checkBox2.setText("Enable");

                    //---- checkBox3 ----
                    checkBox3.setText("Enable");

                    //---- checkBox4 ----
                    checkBox4.setText("Enable");

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(checkBox1))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(checkBox2)
                                            .addComponent(checkBox3)
                                            .addComponent(checkBox4))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(checkBox1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBox2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBox3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBox4)
                                .addContainerGap(97, Short.MAX_VALUE))
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    );
                }
                tabbedPane1.addTab("Anti-ban options", panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(1, 1));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(table1);
                    }
                    panel3.add(scrollPane1);
                }
                tabbedPane1.addTab("About", panel3);
            }
            dialogPane.add(tabbedPane1);
            tabbedPane1.setBounds(0, 0, 365, 225);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane);
        dialogPane.setBounds(new Rectangle(new Point(0, 0), dialogPane.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Gerrit
    private JPanel dialogPane;
    private JButton okButton;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JList<String> list1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
