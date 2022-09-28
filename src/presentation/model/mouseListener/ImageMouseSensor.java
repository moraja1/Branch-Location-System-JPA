package presentation.model.mouseListener;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public abstract class ImageMouseSensor implements MouseInputListener {
    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
    public abstract void mouseClickedOutside(MouseEvent e);
}
