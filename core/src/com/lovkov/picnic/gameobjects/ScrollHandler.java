package com.lovkov.picnic.gameobjects;

public class ScrollHandler {
    private TableCloth tableCloth1, tableCloth2, tableCloth3;
    private Food food1, food2;
    private boolean isScroll;

    public static final int SCROLL_SPEED = -400;
    public static final int GAP = 800;

    public ScrollHandler(float yPos) {
        this.tableCloth1 = new TableCloth(0, yPos, 420, 60, SCROLL_SPEED);
        this.tableCloth2 = new TableCloth(tableCloth1.getTailX(), yPos, 420, 60, SCROLL_SPEED);
        this.tableCloth3 = new TableCloth(tableCloth2.getTailX(), yPos, 420, 60, SCROLL_SPEED);
        this.food1 = new Food(1050, yPos - 70, 300,70, SCROLL_SPEED);
        this.food2 = new Food(food1.getTailX() + GAP, yPos - 70, 300,70, SCROLL_SPEED);
        this.isScroll = true;
    }

    public void update(float delta) {
        tableCloth1.update(delta);
        tableCloth2.update(delta);
        tableCloth3.update(delta);
        food1.update(delta);
        food2.update(delta);

        if (food1.getX() <= 255 && !food1.isScored()) {
            isScroll = false;
            stop();
        }

        if (food2.getX() <= 255 && !food2.isScored()) {
            isScroll = false;
            stop();
        }

        if (food1.isScrolledLeft()) {
            food1.reset(food2.getTailX() + GAP);
        } else if (food2.isScrolledLeft()) {
            food2.reset(food1.getTailX() + GAP);
        }

        if (tableCloth1.isScrolledLeft()) {
            tableCloth1.reset(tableCloth3.getTailX());
        } else if (tableCloth2.isScrolledLeft()) {
            tableCloth2.reset(tableCloth1.getTailX());
        } else if (tableCloth3.isScrolledLeft()) {
            tableCloth3.reset(tableCloth2.getTailX());
        }
    }

    public void scroll() {
        isScroll = true;
        food1.scroll();
        food2.scroll();
        tableCloth1.scroll();
        tableCloth2.scroll();
        tableCloth3.scroll();
    }

    public void stop() {
        isScroll = false;
        food1.stop();
        food2.stop();
        tableCloth1.stop();
        tableCloth2.stop();
        tableCloth3.stop();
    }

    public boolean collides(Swatter swatter) {
        return (food1.collides(swatter) || food2.collides(swatter));
    }

    public Food getCurrentFood() {
        return food1.getX() > 255 ? food2 : food1;
    }

    public boolean isScroll() {
        return isScroll;
    }

    public TableCloth getTableCloth1() {
        return tableCloth1;
    }

    public TableCloth getTableCloth2() {
        return tableCloth2;
    }

    public TableCloth getTableCloth3() {
        return tableCloth3;
    }

    public Food getFood1() {
        return food1;
    }

    public Food getFood2() {
        return food2;
    }
}
