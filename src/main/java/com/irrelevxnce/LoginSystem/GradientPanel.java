package com.irrelevxnce.LoginSystem;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

public class GradientPanel extends JPanel{
	
	Color colorTop;
	Color colorBottom;
	
	public GradientPanel(Color color1, Color color2) {
		this.colorTop = color1;
		this.colorBottom = color2;
	}


	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, colorTop, w, h, colorBottom);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
