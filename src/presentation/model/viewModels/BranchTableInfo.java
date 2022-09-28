package presentation.model.viewModels;

import presentation.model.mouseListener.ImageMouseSensor;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BranchTableInfo extends JLabel implements MouseInputListener {
    private String id;
    private String reference;
    private String address;
    private Double zoning_percentage;
    private String coords;
    private boolean selected = false;

    public BranchTableInfo(String id, String reference, String address, Double zoning_percentage, String coords) {
        this.id = id;
        this.reference = reference;
        this.address = address;
        this.zoning_percentage = zoning_percentage;
        this.coords = coords;

        createPoint();
        addMouseListener(this);
    }
    private void createPoint() {
        int coordX = Integer.parseInt(coords.split(",\\s")[0]);
        int coordY = Integer.parseInt(coords.split(",\\s")[1]);
        setLocation(coordX, coordY);
        setIcon(getPointerImage());

        setVisible(true);
        setEnabled(true);
        setFocusable(true);
        repaint();
    }

    private Icon getPointerImage() {
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
    public String getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getZoning_percentage() {
        return zoning_percentage;
    }

    public void setZoning_percentage(Double zoning_percentage) {
        this.zoning_percentage = zoning_percentage;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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

    @Override
    public void mouseDragged(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.consume();
    }
    public void mouseClikedOutside(MouseEvent e){
        selected = false;
        setIcon(getPointerImage());
        repaint();
        e.consume();
    }
}
