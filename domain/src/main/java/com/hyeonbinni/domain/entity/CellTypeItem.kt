package com.hyeonbinni.domain.entity

import com.hyeonbinni.domain.CellType

data class CellTypeItem(
    val totalPage: Int,
    val page: Int,
    val items: List<Item>
) {
    open class Item(
        val cellType: CellType
    ) {
        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }
}