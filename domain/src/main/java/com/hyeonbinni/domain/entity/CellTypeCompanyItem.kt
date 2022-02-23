package com.hyeonbinni.domain.entity

import com.hyeonbinni.domain.CellType

class CellTypeCompanyItem(
    cellType: CellType,

    val ranking: Int,

    val interviewDifficulty: Float,

    val name: String,

    val salaryAvg: Int,

    val webSite: String,

    val logoPath: String,

    val interviewQuestion: String,

    val companyId: Int,

    val hasJobPosting: String,

    val rateTotalAvg: Float,

    val industryId: Int,

    val reviewSummary: String,

    val type: String,

    val industryName: String,

    val simpleDesc: String,
) : CellTypeItem.Item(cellType)