package co.hellocode.micro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.baselayout_timeline.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.log

class ConversationActivity() : BaseTimelineActivity() {
    override var url = "https://micro.blog/posts/conversation?id="
    override open var title = ""

    override fun initialLoad() {
        val id = intent.getIntExtra("postID", 0)
        Log.i("Conversation", "id: $id")
        this.url = this.url + id.toString()
        super.initialLoad()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.adapter = ConversationRecyclerAdapter(this.posts)
        recyclerView.adapter = this.adapter
    }

    override fun createPosts(items: JSONArray) {
        for (i in 0 until items.length()) {
            val item = items[i] as JSONObject
            this.posts.add(Post(item))
        }
        Log.i("ConversationActivity createPosts", "posts: ${this.posts}")
        this.posts.reverse()
        Log.i("ConversationActivity createPosts", "posts reversed: ${this.posts}")
    }
}
