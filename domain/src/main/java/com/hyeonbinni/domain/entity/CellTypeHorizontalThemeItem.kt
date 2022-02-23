package com.hyeonbinni.domain.entity

import com.hyeonbinni.domain.CellType

class CellTypeHorizontalThemeItem(
    cellType: CellType,

    val count: Int,

    val themes: List<Theme>
) : CellTypeItem.Item(cellType) {
    data class Theme(
        val color: String,

        val coverImage: String,

        val id: Int,

        val title: String
    )
}