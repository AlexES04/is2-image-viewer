package software.ulpgc.io;

import software.ulpgc.model.Image;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private static final Set<String> extensions = Set.of("jpg", "jpeg", "png");
    private final File[] images;

    public FileImageLoader(File path) {
        this.images = path.listFiles(isImage());
    }

    private FilenameFilter isImage() {
        return (dir, name) -> extensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return images[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return imageAt((i+1) % images.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i - 1 : images.length - 1);
            }
        };
    }


}
