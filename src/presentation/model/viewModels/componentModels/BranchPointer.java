package presentation.model.viewModels.componentModels;

import presentation.model.mouseListener.ImageMouseSensor;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BranchPointer extends JLabel implements MouseInputListener {
    protected boolean selected = false;

    public BranchPointer(String coords) {
        int coordX = Integer.parseInt(coords.split(",\\s")[0]);
        int coordY = Integer.parseInt(coords.split(",\\s")[1]);
        setLocation(coordX, coordY);
        setIcon(getPointerImage());
        addMouseListener(this);

        setVisible(true);
        setEnabled(true);
        setFocusable(true);
    }
    protected Icon getPointerImage() {
        ImageIcon pointer;
        if(!selected){
            pointer = new ImageIcon("src\\resources\\Ubicación no seleccionada.png");
        }else{
            pointer = new ImageIcon("src\\resources\\Ubicación seleccionada.png");
        }
        Image resizer = pointer.getImage();
        resizer = resizer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        pointer.setImage(resizer);
        return pointer;
    }

    private Icon getPointerImage(boolean temporary) {
        ImageIcon pointer;
        if(temporary){
            pointer = new ImageIcon("src\\resources\\Ubicación no seleccionada.png");
        }else{
            pointer = new ImageIcon("src\\resources\\Ubicación seleccionada.png");
        }
        Image resizer = pointer.getImage();
        resizer = resizer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        pointer.setImage(resizer);
        return pointer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getParent().getMouseListeners()[0].mouseClicked(e);
        selected = true;
        setIcon(getPointerImage());
        ((ImageMouseSensor)getParent().getMouseListeners()[0]).mouseClickedOutside(e);
        repaint();
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!selected){
            setIcon(getPointerImage(false));
        }
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!selected){
            setIcon(getPointerImage(true));
        }
        e.consume();
    }
    public void mouseClickedOutside(MouseEvent e){
        selected = false;
        setIcon(getPointerImage());
        repaint();
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.consume();
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        setIcon(getPointerImage());
    }
}
