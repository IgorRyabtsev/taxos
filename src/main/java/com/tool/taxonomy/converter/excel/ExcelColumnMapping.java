package com.tool.taxonomy.converter.excel;

public enum ExcelColumnMapping {
    Sourse("A"),
    Name("B"),
    StudyNumber("C"),
    StudyNumberOld("D"),
    StudyName("E"),
    Species("F"),
    StudyGroup("G"),
    Num("H"),
    Sex("I"),
    Age("J"),
    Route("K"),
    Dose("L"),
    Duration("M"),
    Comment("N"),
    Assay("O"),
    PkAnalysys("P"),
    ConcomitantDrug("Q"),
    ParameterFinal("R"),
    Parameter("S"),
    ParameterComment("T"),
    Value("U"),
    Sd("V"),
    RangeFirst("W"),
    RangeSecond("X"),
    Unit("Y"),
    T("Z"),
    FileName("AA"),
    Page("AB"),
    Radiolabelled("AC"),
    MetabolitesAndEnantiomers("AD"),
    Comcomitant("AE"),
    TissueSpecific("AF"),
    September("AG"),
    December("AH"),
    FebruaryApril("AI"),
    AprilJune("AJ"),
    JuneAugust("AK"),
    AugustOctober("AL"),
    OctoberDecember("AM"),
    DecemberFebruary("AN");

    private final String title;

    ExcelColumnMapping(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
