/*
 * Created by JFormDesigner on Thu Dec 12 18:13:12 CET 2019
 */

package henkslot.View;

import javafx.scene.control.SelectionModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
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
    public boolean use_run_energy_random = false;
    public boolean use_greet_random_player = false;
    public boolean use_zoom_in_out = false;
    Object[] columnNames = {"Anti-ban method", "Enable"};
    Object[][] data = {
            {"Method: Examine random objects", true},
            {"Method: Say random words", true},
            {"Method: Turn screen random", true},
            {"Method: Sleep random", true},
            {"Method: Run energy random", true},
            {"Method: Greet random player", true},
            {"Method: Zoom in or out random", true}
    };
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        @Override
        public Class getColumnClass(int column) {
            for (int row = 0; row < getRowCount(); row++) {
                Object o = getValueAt(row, column);

                if (o != null) {
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
        table1.setModel(model);
        setModal(true);
        table1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    if (row == 0) {
                        use_examine_random_objects = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 1) {
                        use_say_random_words = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 2) {
                        use_turn_screen_random = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 3) {
                        use_sleep_random = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 4) {
                        use_run_energy_random = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 5) {
                        use_greet_random_player = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
                    }

                    if (row == 6) {
                        use_zoom_in_out = (boolean) table1.getModel().getValueAt(e.getFirstRow(), 1);
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
        use_examine_random_objects = (boolean) table1.getModel().getValueAt(0, 1);
        use_say_random_words = (boolean) table1.getModel().getValueAt(1, 1);
        use_turn_screen_random = (boolean) table1.getModel().getValueAt(2, 1);
        use_sleep_random = (boolean) table1.getModel().getValueAt(3, 1);
        use_run_energy_random = (boolean) table1.getModel().getValueAt(4, 1);
        use_greet_random_player = (boolean) table1.getModel().getValueAt(5, 1);
        use_zoom_in_out = (boolean) table1.getModel().getValueAt(6, 1);

        System.out.println(use_examine_random_objects);
        System.out.println(use_say_random_words);
        System.out.println(use_turn_screen_random);
        System.out.println(use_sleep_random);
        System.out.println(use_run_energy_random);
        System.out.println(use_greet_random_player);
        System.out.println(use_zoom_in_out);

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

    public boolean isUse_run_energy_random() {
        return use_run_energy_random;
    }

    public boolean isUse_zoom_in_out() {
        return use_zoom_in_out;
    }

    public boolean isUse_greet_random_player() {
        return use_greet_random_player;
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private JSlider slider1;
    private JLabel label1;

    private void slider1StateChanged(ChangeEvent e) {
        JSlider source;
        source = (JSlider) e.getSource();
        label1.setText("Execute anti-ban method every " + source.getValue() + " seconds");
    }

    public int GetSliderValue() {
        return slider1.getValue();
    }

    private void radioButton3ActionPerformed(ActionEvent e) {
        radioButton4.setSelected(false);
        radioButton5.setSelected(false);
    }

    private void radioButton4ActionPerformed(ActionEvent e) {
        radioButton3.setSelected(false);
        radioButton5.setSelected(false);
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
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JTable table1;

    private void radioButton5ActionPerformed(ActionEvent e) {
        radioButton3.setSelected(false);
        radioButton4.setSelected(false);
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
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        slider1 = new JSlider();
        label1 = new JLabel();

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
                    0, 0, 0, 0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder
                    .BOTTOM, new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD, 12), java.awt.Color.
                    red), dialogPane.getBorder()));
            dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.
                                                   beans.PropertyChangeEvent e) {
                    if ("\u0062or\u0064er".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });
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
                    radioButton3.addActionListener(e -> radioButton3ActionPerformed(e));

                    //---- radioButton4 ----
                    radioButton4.setText("Pick redberries");
                    radioButton4.addActionListener(e -> radioButton4ActionPerformed(e));

                    //---- radioButton5 ----
                    radioButton5.setText("Pick both");
                    radioButton5.setSelected(true);
                    radioButton5.addActionListener(e -> radioButton5ActionPerformed(e));

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

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(1, 1));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(table1);
                    }
                    panel3.add(scrollPane1);
                }
                tabbedPane1.addTab("Anti-ban options", panel3);
            }
            dialogPane.add(tabbedPane1);
            tabbedPane1.setBounds(0, 0, 365, 225);

            //---- slider1 ----
            slider1.setMaximum(3600);
            slider1.setMinimum(60);
            slider1.addChangeListener(e -> slider1StateChanged(e));
            dialogPane.add(slider1);
            slider1.setBounds(5, 250, 270, 15);

            //---- label1 ----
            label1.setText("Execute anti-ban method every 60 seconds");
            dialogPane.add(label1);
            label1.setBounds(10, 230, 255, label1.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < dialogPane.getComponentCount(); i++) {
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
