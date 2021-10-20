package me.jadjer.ansem.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

/*
 * Define an implementation of ContentProvider that stubs out
 * all methods
 */
class StubProvider : ContentProvider() {

    /*
     * Always return true, indicating that the
     * provider loaded correctly.
     */
    override fun onCreate(): Boolean  = true

    /*
     * Return no type for MIME type
     */
    override fun getType(uri: Uri): String?  = null

    /*
     * query() always returns no results
     *
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d("Provider", "query")
        return null
    }

    /*
     * insert() always returns null (no URI)
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d("Provider", "insert")
        return null
    }

    /*
     * delete() always returns "no rows affected" (0)
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d("Provider", "delete")
        return 0
    }

    /*
     * update() always returns "no rows affected" (0)
     */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.d("Provider", "update")
        return 0
    }
}