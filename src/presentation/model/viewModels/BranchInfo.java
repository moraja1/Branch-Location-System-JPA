package presentation.model.viewModels;

import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.componentModels.BranchPointer;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BranchInfo extends BranchPointer {
    private String id;
    private String reference;
    private String address;
    private Double zoning_percentage;
    private String coords;

    public BranchInfo() {
    }

    public BranchInfo(String coords) {
        super(coords);
        this.coords = coords;
    }
    public BranchInfo(String id, String reference, String address, Double zoning_percentage, String coords) {
        super(coords);
        this.id = id;
        this.reference = reference;
        this.address = address;
        this.zoning_percentage = zoning_percentage;
        this.coords = coords;
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
