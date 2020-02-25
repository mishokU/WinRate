package com.example.winrate.domain.helpers

import android.content.Context
import com.example.winrate.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

class OpenSkillsFile {
    companion object {
        fun readSkillsFile(context: Context): List<String> {
            val skills = context.resources.openRawResource(R.raw.skills)
            val br = BufferedReader(InputStreamReader(skills) as Reader)
            return br.readLines()
        }
    }
}