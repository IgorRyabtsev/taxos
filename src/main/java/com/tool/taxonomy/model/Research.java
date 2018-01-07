package com.tool.taxonomy.model;

import javax.persistence.*;

@Entity
@Table(name = "pk")
public class Research {

    @Id
    private Long id;

    @Column(name = "source")
    private String sourse;

    @Column(name = "study_number")
    private String studyNumber;

    @Column(name = "study_number_old")
    private String studyNumberOld;

    @Column(name = "study_name")
    private String studyName;

    @Column(name = "species")
    private String species;

    @Column(name = "study_group")
    private String studyGroup;

    @Column(name = "number")
    private Long number;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private String age;

    @Column(name = "route")
    private String route;

    @Column(name = "dose")
    private String dose;

    @Column(name = "duration")
    private String duration;

    @Column(name = "comment")
    private String comment;

    @Column(name = "assay")
    private String assay;

    @Column(name = "pk_analysys")
    private String pkAnalysys;

    @Column(name = "concomitant_drug")
    private String concomitantDrug;

    @Column(name = "parameter_final")
    private String parameterFinal;

    @Column(name = "parameter_comment")
    private String parameterComment;

    @Column(name = "value")
    private Double value;

    @Column(name = "sd")
    private Double sd;

    @Column(name = "range1")
    private Double rangeFirst;

    @Column(name = "range2")
    private Double rangeSecond;

    @Column(name = "unit")
    private String unit;

    @Column(name = "t")
    private String t;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "page")
    private Long page;

    @Column(name = "radiolabelled")
    private String radiolabelled;

    @Column(name = "metabolites_and_enantiomers")
    private String metabolitesAndEnantiomers;

    @Column(name = "comcomitant")
    private String comcomitant;

    @Column(name = "tissue_specific")
    private String tissueSpecific;

    @Column(name = "september")
    private String september;

    @Column(name = "december")
    private String december;

    @Column(name = "february_april")
    private String februaryApril;

    @Column(name = "april_june")
    private String aprilJune;

    @Column(name = "june_august")
    private String juneAugust;

    @Column(name = "august_october")
    private String augustOctober;

    @Column(name = "october_december")
    private String octoberDecember;

    @Column(name = "december_february_2017")
    private String decemberFebruary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parameter_id")
    private Pkparameter parameter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    public Research() {
    }

    public Research(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getSourse() {
        return sourse;
    }

    public void setSourse(final String sourse) {
        this.sourse = sourse;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(final Drug drug) {
        this.drug = drug;
    }

    public String getStudyNumber() {
        return studyNumber;
    }

    public void setStudyNumber(final String studyNumber) {
        this.studyNumber = studyNumber;
    }

    public String getStudyNumberOld() {
        return studyNumberOld;
    }

    public void setStudyNumberOld(final String studyNumberOld) {
        this.studyNumberOld = studyNumberOld;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(final String studyName) {
        this.studyName = studyName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(final String species) {
        this.species = species;
    }

    public String getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(final String studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(final Long number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(final String route) {
        this.route = route;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(final String dose) {
        this.dose = dose;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getAssay() {
        return assay;
    }

    public void setAssay(final String assay) {
        this.assay = assay;
    }

    public String getPkAnalysys() {
        return pkAnalysys;
    }

    public void setPkAnalysys(final String pkAnalysys) {
        this.pkAnalysys = pkAnalysys;
    }

    public String getConcomitantDrug() {
        return concomitantDrug;
    }

    public void setConcomitantDrug(final String concomitantDrug) {
        this.concomitantDrug = concomitantDrug;
    }

    public String getParameterFinal() {
        return parameterFinal;
    }

    public void setParameterFinal(final String parameterFinal) {
        this.parameterFinal = parameterFinal;
    }

    public Pkparameter getParameter() {
        return parameter;
    }

    public void setParameter(final Pkparameter parameter) {
        this.parameter = parameter;
    }

    public String getParameterComment() {
        return parameterComment;
    }

    public void setParameterComment(final String parameterComment) {
        this.parameterComment = parameterComment;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(final Double sd) {
        this.sd = sd;
    }

    public Double getRangeFirst() {
        return rangeFirst;
    }

    public void setRangeFirst(final Double rangeFirst) {
        this.rangeFirst = rangeFirst;
    }

    public Double getRangeSecond() {
        return rangeSecond;
    }

    public void setRangeSecond(final Double rangeSecond) {
        this.rangeSecond = rangeSecond;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public String getT() {
        return t;
    }

    public void setT(final String t) {
        this.t = t;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(final Long page) {
        this.page = page;
    }

    public String getRadiolabelled() {
        return radiolabelled;
    }

    public void setRadiolabelled(final String radiolabelled) {
        this.radiolabelled = radiolabelled;
    }

    public String getMetabolitesAndEnantiomers() {
        return metabolitesAndEnantiomers;
    }

    public void setMetabolitesAndEnantiomers(final String metabolitesAndEnantiomers) {
        this.metabolitesAndEnantiomers = metabolitesAndEnantiomers;
    }

    public String getComcomitant() {
        return comcomitant;
    }

    public void setComcomitant(final String comcomitant) {
        this.comcomitant = comcomitant;
    }

    public String getTissueSpecific() {
        return tissueSpecific;
    }

    public void setTissueSpecific(final String tissueSpecific) {
        this.tissueSpecific = tissueSpecific;
    }

    public String getSeptember() {
        return september;
    }

    public void setSeptember(final String september) {
        this.september = september;
    }

    public String getDecember() {
        return december;
    }

    public void setDecember(final String december) {
        this.december = december;
    }

    public String getFebruaryApril() {
        return februaryApril;
    }

    public void setFebruaryApril(final String februaryApril) {
        this.februaryApril = februaryApril;
    }

    public String getAprilJune() {
        return aprilJune;
    }

    public void setAprilJune(final String aprilJune) {
        this.aprilJune = aprilJune;
    }

    public String getJuneAugust() {
        return juneAugust;
    }

    public void setJuneAugust(final String juneAugust) {
        this.juneAugust = juneAugust;
    }

    public String getAugustOctober() {
        return augustOctober;
    }

    public void setAugustOctober(final String augustOctober) {
        this.augustOctober = augustOctober;
    }

    public String getOctoberDecember() {
        return octoberDecember;
    }

    public void setOctoberDecember(final String octoberDecember) {
        this.octoberDecember = octoberDecember;
    }

    public String getDecemberFebruary() {
        return decemberFebruary;
    }

    public void setDecemberFebruary(final String decemberFebruary) {
        this.decemberFebruary = decemberFebruary;
    }
}
