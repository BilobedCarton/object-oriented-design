package cs3500.animator.view.graphics;

import cs3500.animator.control.IAnimationController;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a slider meant to scrub through an animation's ticks.
 */
public class TickScrubberSlider extends JSlider {
    private IAnimationController controller;

    /**
     * Creates a new {@code TickScrubberSlider} object.
     * @param controller is the controller linked to this slider.
     */
    public TickScrubberSlider(IAnimationController controller) {
        super(1, controller.getLastTick());
        this.controller = controller;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setValue(controller == null ? 1 : controller.getCurrTick());
        super.paintComponent(g);
    }
}

