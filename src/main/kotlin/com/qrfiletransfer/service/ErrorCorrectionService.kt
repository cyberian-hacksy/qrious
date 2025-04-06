package com.qrfiletransfer.service

import com.qrfiletransfer.model.ErrorCorrectionLevel

/**
 * Interface for error correction operations
 * Handles generating and applying error correction data
 */
interface ErrorCorrectionService {
    /**
     * Generate error correction data for a byte array
     * @param data The original data
     * @param level The error correction level
     * @return The error correction data
     */
    fun generateErrorCorrectionData(data: ByteArray, level: ErrorCorrectionLevel): ByteArray

    /**
     * Apply error correction to recover corrupted data
     * @param data The potentially corrupted data
     * @param errorCorrectionData The error correction data
     * @return The repaired data if possible, or null if unrecoverable
     */
    fun applyErrorCorrection(data: ByteArray, errorCorrectionData: ByteArray): ByteArray?

    /**
     * Check if data can be recovered using the error correction data
     * @param data The potentially corrupted data
     * @param errorCorrectionData The error correction data
     * @return True if the data can be recovered
     */
    fun canRecover(data: ByteArray, errorCorrectionData: ByteArray): Boolean
}