package de.c1bergh0st.eberstein3d.overview;

import de.c1bergh0st.eberstein3d.math.Line;
import de.c1bergh0st.eberstein3d.math.Vector;

import java.awt.*;
import java.util.List;

public interface Drawable {

    List<Line> getLines();

    Color getColor();
}
