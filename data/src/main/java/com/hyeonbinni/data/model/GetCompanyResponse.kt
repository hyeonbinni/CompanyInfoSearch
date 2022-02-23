package com.hyeonbinni.data.model

import com.google.gson.annotations.SerializedName
import com.hyeonbinni.domain.*
import com.hyeonbinni.domain.entity.CellTypeCompanyItem
import com.hyeonbinni.domain.entity.CellTypeHorizontalThemeItem
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.entity.CellTypeReviewItem

data class GetCompanyResponse(
    @SerializedName("minimum_interviews")
    val minimumInterviews: Int,

    @SerializedName("total_page")
    val totalPage: Int,

    @SerializedName("minimum_reviews")
    val minimumReviews: Int,

    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<Item>,

    @SerializedName("page")
    val page: Int,

    @SerializedName("page_size")
    val pageSize: Int,

    @SerializedName("minimum_salaries")
    val minimumSalaries: Int
) {
    data class Item(
        @SerializedName("ranking")
        val ranking: Int,

        @SerializedName("cell_type")
        val cellType: String,

        @SerializedName("cons")
        val cons: String,

        @SerializedName("interview_difficulty")
        val interviewDifficulty: Float,

        @SerializedName("name")
        val name: String,

        @SerializedName("days_ago")
        val daysAgo: Int,

        @SerializedName("salary_avg")
        val salaryAvg: Int,

        @SerializedName("web_site")
        val webSite: String,

        @SerializedName("logo_path")
        val logoPath: String,

        @SerializedName("interview_question")
        val interviewQuestion: String,

        @SerializedName("pros")
        val pros: String,

        @SerializedName("company_id")
        val companyId: Int,

        @SerializedName("occupation_name")
        val occupationName: String,

        @SerializedName("has_job_posting")
        val hasJobPosting: String,

        @SerializedName("rate_total_avg")
        val rateTotalAvg: Float,

        @SerializedName("industry_id")
        val industryId: Int,

        @SerializedName("date")
        val date: String,

        @SerializedName("review_summary")
        val reviewSummary: String,

        @SerializedName("type")
        val type: String,

        @SerializedName("industry_name")
        val industryName: String,

        @SerializedName("simple_desc")
        val simpleDesc: String,

        @SerializedName("count")
        val count: Int,

        @SerializedName("themes")
        val themes: List<Theme>
    )

    data class Theme(
        @SerializedName("color")
        val color: String,

        @SerializedName("cover_image")
        val coverImage: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String
    )
}

fun GetCompanyResponse.toCellTypeItem(): CellTypeItem {
    return CellTypeItem(
        page = page,
        totalPage = totalPage,
        items = items.map { item ->
            when (item.cellType) {
                CellType.CELL_TYPE_COMPANY.name -> {
                    CellTypeCompanyItem(
                        ranking = item.ranking,
                        cellType = CellType.CELL_TYPE_COMPANY,
                        interviewDifficulty = item.interviewDifficulty,
                        name = item.name,
                        salaryAvg = item.salaryAvg,
                        webSite = item.webSite,
                        logoPath = item.logoPath,
                        interviewQuestion = item.interviewQuestion,
                        companyId = item.companyId,
                        hasJobPosting = item.hasJobPosting,
                        rateTotalAvg = item.rateTotalAvg,
                        industryId = item.industryId,
                        reviewSummary = item.reviewSummary,
                        type = item.type,
                        industryName = item.industryName,
                        simpleDesc = item.simpleDesc
                    )
                }
                CellType.CELL_TYPE_REVIEW.name -> {
                    CellTypeReviewItem(
                        ranking = item.ranking,
                        cellType = CellType.CELL_TYPE_REVIEW,
                        cons = item.cons,
                        name = item.name,
                        daysAgo = item.daysAgo,
                        logoPath = item.logoPath,
                        pros = item.pros,
                        companyId = item.companyId,
                        occupationName = item.occupationName,
                        rateTotalAvg = item.rateTotalAvg,
                        industryId = item.industryId,
                        date = item.date,
                        reviewSummary = item.reviewSummary,
                        type = item.type,
                        industryName = item.industryName,
                        simpleDesc = item.simpleDesc
                    )
                }
                else -> {
                    CellTypeHorizontalThemeItem(
                        cellType = CellType.CELL_TYPE_HORIZONTAL_THEME,
                        count = item.count,
                        themes = item.themes.map { theme ->
                            CellTypeHorizontalThemeItem.Theme(
                                color = theme.color,
                                coverImage = theme.coverImage,
                                id = theme.id,
                                title = theme.title
                            )
                        }
                    )
                }
            }
        }
    )
}