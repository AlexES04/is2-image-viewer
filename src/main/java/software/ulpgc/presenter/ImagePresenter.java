package software.ulpgc.presenter;

import software.ulpgc.model.Image;
import software.ulpgc.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((ImageDisplay.Shift) this::shift);
        this.display.on((ImageDisplay.Released) this::released);
    }

    private void released(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2) image = offset > 0 ? image.prev() : image.next();
        repaint();
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(image.name(), 0);
    }

    private void shift(int offset) {
        display.clear();
        display.paint(image.name(), offset);
        if (offset > 0) display.paint(image.prev().name(), offset - display.getWidth());
        else display.paint(image.next().name(), display.getWidth() + offset);
    }

    public void show(Image image) {
        this.image = image;
        repaint();
    }
}
