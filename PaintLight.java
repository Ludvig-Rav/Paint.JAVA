import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintLight extends JFrame
{
   private Color theColor;
   private JComboBox comboBox;
   private int x;
   private int y;

   private class MListener implements MouseListener, MouseMotionListener
   {

      @Override
      public void mouseDragged(MouseEvent e)
      {
         int index = comboBox.getSelectedIndex();
         if (!e.isMetaDown() && index == 1)
         {
            int endX = e.getX();
            int endY = e.getY();
            draw(endX, endY);
         }

      }

      @Override
      public void mouseMoved(MouseEvent e)
      {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseClicked(MouseEvent e)
      {
         y = e.getY();
         x = e.getX();

         if (e.getButton() == MouseEvent.BUTTON3)
         {
            clear();
         } else
         {
            setChoosenColor();
         }

      }

      @Override
      public void mousePressed(MouseEvent e)
      {
         y = e.getY();
         x = e.getX();

      }

      @Override
      public void mouseReleased(MouseEvent e)
      {
         int endX = e.getX();
         int endY = e.getY();

         if (!e.isMetaDown())
         {
            if (y >= 60)
            {
               drawRectangle(endX, endY);
            }
         }

      }

      @Override
      public void mouseEntered(MouseEvent e)
      {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent e)
      {
         // TODO Auto-generated method stub

      }

   }

   public static void main(String[] args)
   {
      PaintLight frame = new PaintLight();
      frame.setVisible(true);

   }

   @SuppressWarnings("unchecked")
   public PaintLight()
   {
      String[] items = { "Rektangel", "Frihand" };
      comboBox = new JComboBox(items);
      x = 0;
      y = 0;
      setTitle("PaintLight");
      setSize(550, 300);
      setLocation(50, 150);

      setLayout(null);
      setResizable(false);

      Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.YELLOW, Color.MAGENTA };

      makeColorArea(0, 0, 75, 30, Color.RED);
      makeColorArea(75, 0, 75, 30, Color.GREEN);
      makeColorArea(150, 0, 75, 30, Color.BLUE);
      makeColorArea(225, 0, 75, 30, Color.CYAN);
      makeColorArea(300, 0, 75, 30, Color.YELLOW);
      makeColorArea(375, 0, 75, 30, Color.MAGENTA);
      for (int i = 0; i < 6; i++)
      {
         makeColorArea(75 * i, 0, 75, 30, colors[i]);
      }
      comboBox.setBounds(450, 0, 100, 30);
      add(comboBox);
      MListener ml = new MListener();
      addMouseListener(ml);
      addMouseMotionListener(ml);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   private void makeColorArea(int x1, int y1, int x2, int y2, Color theColor)
   {
      JPanel p1 = new JPanel();
      p1.setBounds(x1, y1, x2, y2);
      p1.setBackground(theColor);
      add(p1);
   }

   private void clear()
   {
      Graphics g = getGraphics();
      Rectangle r = getBounds();
      g.clearRect(0, 60, r.width, r.height - 60);
      g.dispose();
   }

   private void setChoosenColor()
   {
      if (x >= 0 && x < 75 && y >= 30 && y < 60)
      {
         theColor = Color.RED;
      }
      if (x >= 75 && x < 150 && y >= 30 && y < 60)
      {
         theColor = Color.GREEN;
      }
      if (x >= 150 && x < 225 && y >= 30 && y < 60)
      {
         theColor = Color.BLUE;
      }
      if (x >= 225 && x < 300 && y >= 30 && y < 60)
      {
         theColor = Color.CYAN;
      }
      if (x >= 300 && x < 375 && y >= 30 && y < 60)
      {
         theColor = Color.YELLOW;
      }
      if (x >= 375 && x < 450 && y >= 30 && y < 60)
      {
         theColor = Color.MAGENTA;
      }
   }

   private void drawRectangle(int endX, int endY)
   {
      Graphics g = getGraphics();
      g.setColor(theColor);
      g.fillRect(x, y, endX - x, endY - y);
      g.dispose();
   }

   private void draw(int endX, int endY)
   {
      Graphics g = getGraphics();
      g.setColor(theColor);
      g.drawLine(x, y, endX, endY);
      g.dispose();
      x = endX;
      y = endY;
   }

}
