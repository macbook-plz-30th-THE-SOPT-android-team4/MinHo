package com.example.soptexampleproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RepoData(val repoName: String, val repoInfo: String) : Parcelable
