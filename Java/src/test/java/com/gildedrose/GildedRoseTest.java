package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final int SIX_DAYS = 6;
    private static final int TWENTY_DAYS = 20;
    private GildedRose app;

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @BeforeEach
    void init() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0),
            new Item("Cake", 10, 20),
            new Item("Conjured Cake", 10, 20),
            new Item("Conjured Aged Brie", 2, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Sulfuras, Hand of Ragnaros", 2, 80),
            new Item("Conjured Sulfuras, Hand of Ragnaros", 2, 80)
        };
        app = new GildedRose(items);
    }

    @Test
    void aged_brie_sell_in_2_and_quality_0_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(10, app.items[0].quality);
    }

    @Test
    void cake_sell_in_10_and_quality_20_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(14, app.items[1].quality);
    }

    @Test
    void conjured_cake_sell_in_10_and_quality_20_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(8, app.items[2].quality);
    }

    @Test
    void conjured_aged_brie_sell_in_2_and_quality_0_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(3, app.items[3].quality);
    }

    @Test
    void backstage_sell_in_15_and_quality_20_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(27, app.items[4].quality);
    }

    @Test
    void conjured_backstage_sell_in_15_and_quality_20_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(26, app.items[5].quality);
    }

    @Test
    void conjured_backstage_sell_in_15_and_quality_20_after_20_days() {
        for (int i = 0; i < TWENTY_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(0, app.items[5].quality);
    }

    @Test
    void backstage_sell_in_15_and_quality_20_after_20_days() {
        for (int i = 0; i < TWENTY_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(0, app.items[4].quality);
    }

    @Test
    void sulfuras_sell_in_2_and_quality_80_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(80, app.items[6].quality);
    }

    @Test
    void conjured_sulfuras_sell_in_2_and_quality_80_after_6_days() {
        for (int i = 0; i < SIX_DAYS; i++) {
            app.updateQuality();
        }
        assertEquals(80, app.items[7].quality);
    }

}
