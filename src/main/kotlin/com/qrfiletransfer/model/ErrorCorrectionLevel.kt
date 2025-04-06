package com.qrfiletransfer.model

/**
 * Defines the level of error correction to apply to chunks and QR codes
 */
enum class ErrorCorrectionLevel(val redundancyRate: Double) {
    LOW(0.07),         // Approximately 7% redundancy
    MEDIUM(0.15),      // Approximately 15% redundancy
    HIGH(0.25),        // Approximately 25% redundancy
    VERY_HIGH(0.35);   // Approximately 35% redundancy

    /**
     * Calculate the size of error correction data based on the original data size
     */
    fun calculateECSize(dataSize: Int): Int {
        return (dataSize * redundancyRate).toInt()
    }
}