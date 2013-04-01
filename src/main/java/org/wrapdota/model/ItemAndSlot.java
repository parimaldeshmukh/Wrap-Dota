package org.wrapdota.model;

class ItemAndSlot {

    Integer itemId;
    Integer atSlot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemAndSlot)) return false;

        ItemAndSlot that = (ItemAndSlot) o;

        if (!atSlot.equals(that.atSlot)) return false;
        if (!itemId.equals(that.itemId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + atSlot.hashCode();
        return result;
    }

    public ItemAndSlot(Integer itemId, Integer atSlot){
        this.itemId = itemId;
        this.atSlot = atSlot;
    }
}
