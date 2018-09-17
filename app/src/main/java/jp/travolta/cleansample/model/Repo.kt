package jp.travolta.cleansample.model

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property userId the unique identifier of the author of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property body the content of the post
 */
data class Repo(val name: String, val id: String, val title: String, val body: String)