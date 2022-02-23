package com.hyeonbinni.domain.entity

import com.hyeonbinni.domain.CellType

class CellTypeReviewItem(
    cellType: CellType,

    val ranking: Int,

    val cons: String,

    val name: String,

    val daysAgo: Int,

    val logoPath: String,

    val pros: String,

    val companyId: Int,

    val occupationName: String,

    val rateTotalAvg: Float,

    val industryId: Int,

    val date: String,

    val reviewSummary: String,

    val type: String,

    val industryName: String,

    val simpleDesc: String,
) : CellTypeItem.Item(cellType)