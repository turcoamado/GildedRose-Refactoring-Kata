package com.gildedrose;

/**
 * Assumed considerations:
 * - Sulfuras items isn't reached by the new requirements because it's legendary
 * - Conjured Aged Brie increase its quality in 1 every 2 days
 * - Conjured Backstage [...] increase its quality in same proportion as Conjured Aged Brie but considering detailed scenarios.
 */

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED_AGED_BRIE = "Conjured Aged Brie";
    private final static String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final static String CONJURED_SULFURAS = "Conjured Sulfuras, Hand of Ragnaros";
    private final static String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private final static String CONJURED_BACKSTAGE = "Conjured Backstage passes to a TAFKAL80ETC concert";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals(AGED_BRIE)
                    && !items[i].name.equals(BACKSTAGE)
                    && !items[i].name.equals(CONJURED_AGED_BRIE)
                    && !items[i].name.equals(CONJURED_BACKSTAGE)) {
                if (items[i].quality > 0) {
                    if (isntSulfuras(i)) {
                        items[i].quality = items[i].quality - 1;
                        if (items[i].name.startsWith("Conjured")
                            && !items[i].name.contains(AGED_BRIE))
                            items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    if (items[i].name.equals(CONJURED_AGED_BRIE)) {
                        increaseQualityOfBackstageEvery2Days(i);
                    } else {
                        items[i].quality = items[i].quality + 1;
                    }

                    if (items[i].name.equals(BACKSTAGE)) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }

                    if (items[i].name.equals(CONJURED_BACKSTAGE)) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                increaseQualityOfBackstageEvery2Days(i);
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                increaseQualityOfBackstageEvery2Days(i);
                            }
                        }
                    }
                }
            }

            if (isntSulfuras(i)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals(AGED_BRIE)) {
                    if (isntBackstage(i)) {
                        if (!items[i].name.equals(CONJURED_AGED_BRIE)) {
                            if (items[i].quality > 0) {
                                if (isntSulfuras(i)) {
                                    items[i].quality = items[i].quality - 1;
                                }
                            }
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else {
                    if (items[i].quality < 50) {
                        if (items[i].name.equals(CONJURED_AGED_BRIE)) {
                            if (items[i].sellIn % 2 == 0) {
                                items[i].quality = items[i].quality + 1;
                            }
                        } else {
                            items[i].quality = items[i].quality + 1;
                        }
                    }
                }
            }
        }
    }

    private void increaseQualityOfBackstageEvery2Days(int i) {
        if (items[i].sellIn % 2 != 0) {
            items[i].quality = items[i].quality + 1;
        }
    }

    private boolean isntSulfuras(int i) {
        return !items[i].name.equals(SULFURAS)
            && !items[i].name.equals(CONJURED_SULFURAS);
    }

    private boolean isntBackstage(int i) {
        return !items[i].name.equals(BACKSTAGE)
            && !items[i].name.equals(CONJURED_BACKSTAGE);
    }
}
