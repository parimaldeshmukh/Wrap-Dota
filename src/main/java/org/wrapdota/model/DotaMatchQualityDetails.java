package org.wrapdota.model;

public class DotaMatchQualityDetails {
    Integer positiveVotes;
    Integer negativeVotes;

    public DotaMatchQualityDetails(Integer positiveVotes, Integer negativeVotes) {
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DotaMatchQualityDetails)) return false;

        DotaMatchQualityDetails that = (DotaMatchQualityDetails) o;

        if (!negativeVotes.equals(that.negativeVotes)) return false;
        if (!positiveVotes.equals(that.positiveVotes)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = positiveVotes.hashCode();
        result = 31 * result + negativeVotes.hashCode();
        return result;
    }
}
