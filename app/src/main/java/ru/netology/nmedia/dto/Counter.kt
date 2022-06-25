package ru.netology.nmedia.dto

class Counter {
    fun counterConversion(count: Int): String {
        val formatCount = when {
            count in 1000..9999 -> {
                String.format("%.1fK", count / 1000.0)
            }
            count in 10_000..999_999 -> {
                String.format("%dK", count / 1000)
            }
            count >= 1_000_000 -> {
                String.format("%.1fM", count / 1_000_000.0)
            }
            else -> {
                count.toString()
            }
        }
        return formatCount
    }
}