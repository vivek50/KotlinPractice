package com.example.vivek50.contactlist

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog

class MainActivity : AppCompatActivity() {

    private lateinit var contactsListView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactsListView = findViewById(R.id.listContacts)
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                    1234)
        } else {
            fetchContacts()
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1234) {
            if (permissions.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchContacts()
                } else {
                    MaterialDialog(this).show {
                        title(text = "Require Permission")
                        message(text = "We Need require your permission for get Contact List")
                        positiveButton(text = "OK") {
                            ActivityCompat.requestPermissions(this@MainActivity,
                                    arrayOf(Manifest.permission.READ_CONTACTS), 1234)
                        }
                        negativeButton(text = "Cancel") { finish() }
                        cancelable(false)
                        cancelOnTouchOutside(false)
                    }
                }
            }
        }

    }

    private var displayName : String = ""
    private var emailAddress : String = ""
    private var phoneNumber : String = ""
    private var contactList : MutableList<String> = mutableListOf()

    private fun fetchContacts() {
        contactList.clear()
        val resolver : ContentResolver = contentResolver
        val cursor : Cursor? = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                displayName = ""; emailAddress = ""; phoneNumber = ""
                displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val id : String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val emails : Cursor? = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id,
                        null, null)
                if (emails != null) {
                    while (emails.moveToNext()) {
                        emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        break
                    }
                    emails.close()
                }
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val phones : Cursor? = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null);
                    if (phones != null) {
                        while (phones.moveToNext()) {
                            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            break
                        }
                        phones.close()
                    }
                }
                contactList.add("DisplayName: $displayName, PhoneNumber: $phoneNumber, EmailAddress: $emailAddress\n")
            }
            cursor.close()
        }
        Log.d("@Modi", "Size : " + contactList.size)
        contactsListView.adapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_list_item_1, contactList)
    }
}