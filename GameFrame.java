/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.whowantstobeamillionaire;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.*;
/**
 *
 * @author Aisen Sousuke
 */
public class GameFrame extends javax.swing.JFrame {

    ArrayList<Question> questions = new ArrayList<Question>();
        private Random  rnd = new Random();
        public int Level =0;
        Question currentQuestion;
        
        Connection co;
    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        initComponents();
        SelectFromDatabase();
        //ReadFile();
        startGame();
        
    }
    private void ReadFile()
        {
            try{
                FileInputStream fstream = new FileInputStream
        ("C://Users//Aisen Sousuke//OneDrive//Документы//NetBeansProjects//WhoWantsToBeAMillionaire//src//main//java//com//mycompany//whowantstobeamillionaire//Вопросы.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "UTF-8"));
                String strLine;
                //OpenDatabase();

                while ((strLine = br.readLine()) != null) {
                    String[] s = strLine.split("\t");
                    questions.add(new Question(s));
                    //InsertIntoDatabase(s);
                }
                //CloseDatabase();
            } catch (IOException e) {
                System.out.println("Ошибка");
            }
        }
    void OpenDatabase()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection ("jdbc:sqlite:C:\\Users\\Aisen Sousuke\\OneDrive\\Документы\\NetBeansProjects\\WhoWantsToBeAMillionaire\\src\\main\\java\\com\\mycompany\\whowantstobeamillionaire\\вопросы.sqlite");
            JOptionPane.showMessageDialog(null, "вопросы.sqlite открыта");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка " + e.getMessage());
        }
    }
    
    void CloseDatabase()
    {
        try {
            co.close();
            JOptionPane.showMessageDialog(null, "вопросы.sqlite закрыта");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка " + e.getMessage());}
    }
    private void InsertIntoDatabase(String[] s)
    {   String query = "INSERT INTO questions (question, answer1, answer2, answer3, answer4, rightanswer, level)" + 
                "VALUES (";
        for (int i=0; i<6; i++)
            query += "'" + s[i] + "', ";
        query += "'" + Integer.parseInt(s[6]) + "')";
        try {
            Statement st = co.createStatement();
            st.executeUpdate(query);
            st.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка " + e.getMessage());}
    }
    
    private void SelectFromDatabase(){
        OpenDatabase();
        try{
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("SELECT question, answer1, answer2, answer3, answer4, rightanswer, level "
                    + "FROM questions");
            while (rs.next())
            {
                String[] s = new String[7];
                for (int i =0; i<7;i++)
                    s[i] = rs.getString(i+1);
                questions.add(new Question(s));
            }
            st.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка " + e.getMessage());}
        
        CloseDatabase();
    }
    
    private void ShowQuestion(Question q)
    {
        lblQuestionText.setText(q.Text);
        btnAnswer1.setText(q.Answers[0]);
        btnAnswer2.setText(q.Answers[1]);
        btnAnswer3.setText(q.Answers[2]);
        btnAnswer4.setText(q.Answers[3]);
    }

    
    private Question GetQuestion(int level)
    {
        List<Question> list = 
                questions.stream().filter(q->q.Level==level).collect(Collectors.toList());
        return list.get(rnd.nextInt(list.size()));
    }
    
    private void NextStep()
    {
        JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, 
            btnAnswer3, btnAnswer4};
        
        for(JButton btn: btns)
            btn.setEnabled(true);
        
        Level++;
        if (Level > 15)
        {
            JOptionPane.showMessageDialog(this, "Вау победа");
            startGame();
        }
            
        currentQuestion = GetQuestion(Level);
        ShowQuestion(currentQuestion);
        lstLevel.setSelectedIndex(lstLevel.getModel().getSize()-Level);
    }
    
    private void startGame()
    {
        Level = 0;
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        NextStep();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLevel = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblQuestionText = new javax.swing.JLabel();
        btnAnswer1 = new javax.swing.JButton();
        btnAnswer3 = new javax.swing.JButton();
        btnAnswer2 = new javax.swing.JButton();
        btnAnswer4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aisen Sousuke\\OneDrive\\Документы\\NetBeansProjects\\WhoWantsToBeAMillionaire\\src\\main\\java\\com\\mycompany\\whowantstobeamillionaire\\picture.jpg")); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lstLevel.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "3 000 000", "1 500 000", "   800 000", "   400 000", "   200 000", "   100 000", "     50 000", "     25 000", "     15 000", "     10 000", "       5 000", "       3 000", "       2 000", "       1 000", "          500" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstLevel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        jButton1.setText("50/50");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Помощь зала");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Звонок другу");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Забрать деняги");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblQuestionText.setText("lblQuestionText");
        lblQuestionText.setName(""); // NOI18N

        btnAnswer1.setText("jButton5");
        btnAnswer1.setActionCommand("1");
        btnAnswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAnswerPerformed(evt);
            }
        });

        btnAnswer3.setText("jButton6");
        btnAnswer3.setActionCommand("3");
        btnAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAnswerPerformed(evt);
            }
        });

        btnAnswer2.setText("jButton7");
        btnAnswer2.setActionCommand("2");
        btnAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAnswerPerformed(evt);
            }
        });

        btnAnswer4.setText("jButton8");
        btnAnswer4.setActionCommand("4");
        btnAnswer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAnswerPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnAnswer4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuestionText, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(41, 41, 41)
                        .addComponent(jButton4))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblQuestionText)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnswer1)
                    .addComponent(btnAnswer3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnswer2)
                    .addComponent(btnAnswer4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntAnswerPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAnswerPerformed
        if (currentQuestion.RightAnswer.equals(evt.getActionCommand()))
            NextStep();            
        else
        {
            JOptionPane.showMessageDialog(this, "Неверный ответ!");
            startGame();
        }
    }//GEN-LAST:event_bntAnswerPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, 
            btnAnswer3, btnAnswer4};
        
        int count = 0;
        while (count<2)
        {
            int n = rnd.nextInt(4);
            String ac = btns[n].getActionCommand();
            
            if (!ac.equals(currentQuestion.RightAnswer)
                    && btns[n].isEnabled())
            {
                btns[n].setEnabled(false);
                count++;
            }
        }
        jButton1.setEnabled(false);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(this, "Допустим, зал вам помог.");
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(this, "Друг не отвечает.");
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JOptionPane.showMessageDialog(this, "Поздравляем.");
        startGame();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnswer1;
    private javax.swing.JButton btnAnswer2;
    private javax.swing.JButton btnAnswer3;
    private javax.swing.JButton btnAnswer4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuestionText;
    private javax.swing.JList<String> lstLevel;
    // End of variables declaration//GEN-END:variables
}
