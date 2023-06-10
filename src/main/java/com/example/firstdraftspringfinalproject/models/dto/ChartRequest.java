package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;

public class ChartRequest {

    private MetricsCategory primaryCategory;

    private Boolean doesSecondaryCategoryExist;

    private String primaryCategoryTopic;

    private MetricsCategory secondaryCategory;

    public MetricsCategory getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public Boolean getDoesSecondaryCategoryExist() {
        return doesSecondaryCategoryExist;
    }

    public void setDoesSecondaryCategoryExist(Boolean doesSecondaryCategoryExist) {
        this.doesSecondaryCategoryExist = doesSecondaryCategoryExist;
    }

    public String getPrimaryCategoryTopic() {
        return primaryCategoryTopic;
    }

    public void setPrimaryCategoryTopic(String primaryCategoryTopic) {
        this.primaryCategoryTopic = primaryCategoryTopic;
    }

    public MetricsCategory getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(MetricsCategory secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public boolean hasPrimaryMetricsCategoryOnly() {
        return this.secondaryCategory.equals(MetricsCategory.NOSECONDARYCATEGORY);
    }

    public boolean hasSecondaryMetricsCategory() {
        if(primaryCategory.equals(secondaryCategory)){
            throw new RuntimeException("Primary Category and secondary category cannot be the same.");
        }
        return !this.secondaryCategory.equals(MetricsCategory.NOSECONDARYCATEGORY);
    }
}
