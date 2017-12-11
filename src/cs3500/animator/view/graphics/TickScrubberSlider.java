package cs3500.animator.view.graphics;

import cs3500.animator.control.IAnimationController;

import javax.swing.*;
import java.awt.*;

public class TickScrubberSlider extends JSlider {
    private IAnimationController controller;

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

