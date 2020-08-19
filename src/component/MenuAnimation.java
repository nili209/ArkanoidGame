package component;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> keys = new ArrayList<String>();
    private List<String> keysSubMenu = new ArrayList<String>();
    private List<String> messages = new ArrayList<String>();
    private List<String> messagesSubMenu = new ArrayList<String>();
    private List<Menu<T>> subMenu = new ArrayList<Menu<T>>();
    private List<T> returnVals = new ArrayList<T>();
    private T status;
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private boolean isAlreadyPreesed = true;

    /**
     * Instantiates a new Menu animation.
     *
     * @param sensor          the sensor
     * @param animationRunner the animation runner
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner animationRunner) {
        this.status = null;
        this.stop = false;
        this.keyboardSensor = sensor;
        this.animationRunner = animationRunner;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(returnVal);
        this.status = returnVal;
    }

    @Override
    public T getStatus() {
        this.stop = false;
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> s) {
        this.keysSubMenu.add(key);
        this.messagesSubMenu.add(message);
        this.subMenu.add(s);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Color color1 = new Color(0xABB8B3);
        Color color = new Color(0x9B7299);
        d.setColor(color1);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(color);
        d.fillCircle(790, 590, 200);
        d.setColor(color1);
        d.fillCircle(790, 590, 180);
        d.setColor(color);
        d.fillCircle(790, 590, 160);
        d.setColor(color1);
        d.fillCircle(790, 590, 140);
        d.setColor(color);
        d.fillCircle(790, 590, 120);
        d.setColor(color1);
        d.fillCircle(790, 590, 100);
        d.setColor(color);
        d.fillCircle(790, 590, 80);
        d.setColor(color1);
        d.fillCircle(790, 590, 60);
        d.setColor(color);
        d.fillCircle(790, 590, 40);
        d.setColor(color1);
        d.fillCircle(790, 590, 20);
        d.setColor(color);
        // d.fillCircle(790,590,10);
        d.drawText(100, 70, "Arkanoid", 45);
        int x = 100;
        int y = 125;
        for (String s : this.messages) {
            d.drawText(x, y, s, 25);
            y += 30;
        }
        for (String s : this.messagesSubMenu) {
            d.drawText(x, y, s, 25);
            y += 30;
        }
        int index = 0;
        for (String k : this.keys) {
            if (this.keyboardSensor.isPressed(k)) {
                if (isAlreadyPreesed) {
                    return;
                } else {
                    this.stop = true;
                    index = this.keys.indexOf(k);
                    this.status = this.returnVals.get(index);
                    isAlreadyPreesed = true;
                    break;
                }
            } else {
                this.isAlreadyPreesed = false;
            }
        }
        int index1 = 0;
        for (String k : this.keysSubMenu) {
            if (this.keyboardSensor.isPressed(k)) {
                if (isAlreadyPreesed) {
                    return;
                } else {
                    this.stop = true;
                    index1 = this.keysSubMenu.indexOf(k);
                    Menu<T> menu = this.subMenu.get(index1);
                    animationRunner.run(menu);
                    this.status = menu.getStatus();
                    isAlreadyPreesed = true;
                    break;
                }
            } else {
                this.isAlreadyPreesed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
