package TresEnRaya;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TresEnRaya extends JFrame{  
	JButton[] boton = new JButton[9];//boton 9个按键
	static final char hueco = 32; //定义局势情况参数
	static int pinchanum = 0;
	static final int gana1 = +100;
	static final int gana2 = -100;
	static final int empate = 0;
	static final int[][] gana_condicion = {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6},
	};
	
  public TresEnRaya(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //绘制棋盘
	this.setSize(400, 400);  
	this.setLayout(new GridLayout(3,3));  //布局管理器函数
	this.setTitle("TresEnRaya");
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	this.setLocation((screen.height - this.getHeight())/2,(screen.width - this.getWidth())/5);//置中
	for (int i=0; i<9; i++) {                      
		boton[i] = new JButton("");
		boton[i].setFont(new Font("Arial",Font.BOLD,30));
		boton[i].addActionListener(new jugador());
		this.add(boton[i]); 
				
  }
	this.setVisible(true);
}
  public  static void main(String[] args) { //开局提示
	  new TresEnRaya();
	  JOptionPane.showMessageDialog(null, "0 significa jugador1,\nX significa jugador2.","atension",JOptionPane.DEFAULT_OPTION);
  }

   private class jugador implements ActionListener{

	 
		public void actionPerformed(ActionEvent e) { //走棋每一步的判断是0还是x
			for(int i=0; i<9; i++){
				if (e.getSource() == boton[i]){
			        if(++TresEnRaya.pinchanum % 2 == 0){
			        	boton[i].setText("X");
				    }else{
					    boton[i].setText("0"); //0
				}
				boton[i].setEnabled(false);//走过的不能再走
		}}
		

	int juego_condicion = juego_Condicion(boton);  //输赢提示

           switch (juego_condicion){
           case gana1:
           JOptionPane.showMessageDialog(null, "jugador 1( 0 ) gana","atencion",JOptionPane.DEFAULT_OPTION);
           break;
           case gana2:
           JOptionPane.showMessageDialog(null, "jugador 2( X ) gana","atencion",JOptionPane.DEFAULT_OPTION);
           break;
           case empate:
           JOptionPane.showMessageDialog(null, "empate","atencion",JOptionPane.DEFAULT_OPTION);
           }
           if (juego_condicion == gana1 || juego_condicion == gana2 || juego_condicion == empate){//重下提示
        	   Object[] options ={ "SI", "NO" };
        	   int seacabo = JOptionPane.showConfirmDialog(null, "juega otra vez?","atencion",JOptionPane.YES_NO_OPTION,
        			   JOptionPane.QUESTION_MESSAGE);
        	
        	   if (seacabo == JOptionPane.YES_OPTION){  //恢复棋盘
        		   for (int i=0; i<9; i++){
        			   boton[i].setText("");
        			   boton[i].setEnabled(true);
        		   }
        	   }else{
        		   System.exit(0);
        	   }
           }
		}
   }

	public int juego_Condicion(JButton[] boton) {
		
	   int result = 1;
	   boolean lleno =true; 
       for (int[] status:gana_condicion){//遍历赢的数组
			char chess = boton[status[0]].getText().charAt(0);
			if (chess==hueco){
				continue;//数组第一个数所在位置为空，调n下一个数组
			}
			if ((boton[status[1]].getText().charAt(0) == chess) && (chess == boton[status[2]].getText().charAt(0))){//其余两个都为一个符号					
				result = (chess == '0' ? gana1 : gana2);
			    break;
			}
       }
       if (result!=gana1&&result!=gana2){	   
    	   for (int pos =0; pos<9; pos++){
    			  char chess1 = boton[pos].getText().charAt(0);
    			  if (hueco == chess1){   //遍历棋盘，若有空格跳出继续下
    					lleno = false;
    					break;
    					}
    	   }
    	   }
	   
	   if (result != gana1 & result != gana2 & lleno)
  			result = empate;
	   return result;}  

}