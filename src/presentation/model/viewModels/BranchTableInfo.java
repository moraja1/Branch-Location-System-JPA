package presentation.model.viewModels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BranchTableInfo {
    private String id;
    private String reference;
    private String address;
    private Double zoning_percentage;
    private String coords;
    private JLabel point;

    public BranchTableInfo(String id, String reference, String address, Double zoning_percentage, String coords) {
        this.id = id;
        this.reference = reference;
        this.address = address;
        this.zoning_percentage = zoning_percentage;
        this.coords = coords;

        createPoint();
    }
    private void createPoint() {
        int coordX = Integer.parseInt(coords.split(",\\s")[0]);
        int coordY = Integer.parseInt(coords.split(",\\s")[1]);
        point = new JLabel();
        point.setLocation(coordX, coordY);
        point.setIcon(getPointerImage(false));
        point.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if((JLabel)e.getComponent() == point){
                    point.setIcon(getPointerImage(true));
                }else{
                    point.setIcon(getPointerImage(false));
                }
            }
        });
        point.setVisible(true);
        point.setEnabled(true);
        point.setFocusable(true);
    }

    private Icon getPointerImage(boolean selected) {
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
    public JLabel getPoint() {
        return point;
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
}
