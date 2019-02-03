package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void textFixtureConjuredNotWorkingYetTest() {

        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);


        // ------ day 0 --------

        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(10, items[0].sellIn);
        assertEquals(20, items[0].quality);

        assertEquals("Aged Brie", items[1].name);
        assertEquals(2, items[1].sellIn);
        assertEquals(0, items[1].quality);

        assertEquals("Elixir of the Mongoose", items[2].name);
        assertEquals(5, items[2].sellIn);
        assertEquals(7, items[2].quality);

        assertEquals("Sulfuras, Hand of Ragnaros", items[3].name);
        assertEquals(0, items[3].sellIn);
        assertEquals(80, items[3].quality);

        assertEquals("Sulfuras, Hand of Ragnaros", items[4].name);
        assertEquals(-1, items[4].sellIn);
        assertEquals(80, items[4].quality);


        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[5].name);
        assertEquals(15, items[5].sellIn);
        assertEquals(20, items[5].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[6].name);
        assertEquals(10, items[6].sellIn);
        assertEquals(49, items[6].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[7].name);
        assertEquals(5, items[7].sellIn);
        assertEquals(49, items[7].quality);

        // Not working yet as supposed
        assertEquals("Conjured Mana Cake", items[8].name);
        assertEquals(3, items[8].sellIn);
        assertEquals(6, items[8].quality);

        // ------ day 1--------

        app.updateQuality();


        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);

        assertEquals("Aged Brie", items[1].name);
        assertEquals(1, items[1].sellIn);
        assertEquals(1, items[1].quality);

        assertEquals("Elixir of the Mongoose", items[2].name);
        assertEquals(4, items[2].sellIn);
        assertEquals(6, items[2].quality);

        assertEquals("Sulfuras, Hand of Ragnaros", items[3].name);
        assertEquals(0, items[3].sellIn);
        assertEquals(80, items[3].quality);

        assertEquals("Sulfuras, Hand of Ragnaros", items[4].name);
        assertEquals(-1, items[4].sellIn);
        assertEquals(80, items[4].quality);


        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[5].name);
        assertEquals(14, items[5].sellIn);
        assertEquals(21, items[5].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[6].name);
        assertEquals(9, items[6].sellIn);
        assertEquals(50, items[6].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[7].name);
        assertEquals(4, items[7].sellIn);
        assertEquals(50, items[7].quality);

        // Not working yet as supposed
        assertEquals("Conjured Mana Cake", items[8].name);
        assertEquals(2, items[8].sellIn);
        assertEquals(5, items[8].quality);

    }

    @Test
    public void normalItemsTest() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 1, 20),
                new Item("Elixir of the Mongoose", 0, 4)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        // For normal items decrease quality by 1 each day.
        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(0, items[0].sellIn);
        assertEquals(19, items[0].quality);

        //Decrease quality by 2 if sell day has passed. Once the sell by date has passed, Quality degrades twice as fast
        assertEquals("Elixir of the Mongoose", items[1].name);
        assertEquals(-1, items[1].sellIn);
        assertEquals(2, items[1].quality);

        app.updateQuality();

        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(-1, items[0].sellIn);
        assertEquals(17, items[0].quality);

        // The Quality of an item is never negative
        assertEquals("Elixir of the Mongoose", items[1].name);
        assertEquals(-2, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();

        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(-2, items[0].sellIn);
        assertEquals(15, items[0].quality);

        assertEquals("Elixir of the Mongoose", items[1].name);
        assertEquals(-3, items[1].sellIn);
        assertEquals(0, items[1].quality);

    }

    @Test
    public void agedBrieItemsTest() {
        Item[] items = new Item[]{
                new Item("Aged Brie", 2, 45)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        // "Aged Brie" actually increases in Quality the older it gets
        assertEquals("Aged Brie", items[0].name);
        assertEquals(1, items[0].sellIn);
        assertEquals(46, items[0].quality);

        app.updateQuality();
        app.updateQuality();


        assertEquals("Aged Brie", items[0].name);
        assertEquals(-1, items[0].sellIn);
        assertEquals(49, items[0].quality);

        app.updateQuality();
        app.updateQuality();

        // The Quality of an item is never more than 50
        assertEquals("Aged Brie", items[0].name);
        assertEquals(-3, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void sulfurasTest() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 2, 45),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
        assertEquals("Sulfuras, Hand of Ragnaros", items[0].name);
        assertEquals(2, items[0].sellIn);
        assertEquals(45, items[0].quality);

        assertEquals("Sulfuras, Hand of Ragnaros", items[1].name);
        assertEquals(-1, items[1].sellIn);
        assertEquals(80, items[1].quality);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
        assertEquals("Sulfuras, Hand of Ragnaros", items[0].name);
        assertEquals(2, items[0].sellIn);
        assertEquals(45, items[0].quality);

        assertEquals("Sulfuras, Hand of Ragnaros", items[1].name);
        assertEquals(-1, items[1].sellIn);
        assertEquals(80, items[1].quality);

    }

    @Test
    public void backstagePasses() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 12, 30),
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 46),
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        // "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(11, items[0].sellIn);
        assertEquals(31, items[0].quality);

        // Quality drops to 0 after the concert
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-2, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();


        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(10, items[0].sellIn);
        assertEquals(32, items[0].quality);

        // Quality drops to 0 after the concert
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-3, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();

        // Quality increases by 2 when there are 10 days or less...
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(9, items[0].sellIn);
        assertEquals(34, items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-4, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        // and by 3 when there are 5 days or less
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(4, items[0].sellIn);
        assertEquals(45, items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-9, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(3, items[0].sellIn);
        assertEquals(48, items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-10, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(2, items[0].sellIn);
        assertEquals(50, items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-11, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(0, items[0].sellIn);
        assertEquals(50, items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-13, items[1].sellIn);
        assertEquals(0, items[1].quality);

        app.updateQuality();
        app.updateQuality();

        // Quality drops to 0 after the concert
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
        assertEquals(-2, items[0].sellIn);
        assertEquals(0, items[0].quality);

        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[1].name);
        assertEquals(-15, items[1].sellIn);
        assertEquals(0, items[1].quality);

    }



}
