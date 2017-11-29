package org.shadowice.flocke.andotp.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.shadowice.flocke.andotp.R;

public class EntryThumbnail {
    private enum AssetType {
        Bitmap,
        Vector
    }

    public enum EntryThumbnails {
        Default(R.mipmap.ic_launcher_round),
        Amazon(R.drawable.ic_amazon),
        AngelList(R.drawable.ic_angellist),
        Apple(R.drawable.ic_apple),
        BattleNet(R.drawable.ic_battlenet),
        BitBucket(R.drawable.ic_bitbucket),
        Bitcoin(R.drawable.ic_bitcoin),
        Bitstamp(R.mipmap.bitstamp, AssetType.Bitmap),
        Bitwarden(R.mipmap.bitwarden, AssetType.Bitmap),
        Cloudflare(R.drawable.ic_cloudflare),
        Coinbase(R.drawable.ic_coinbase),
        CozyCloud(R.drawable.ic_cozycloud),
        DigitalOcean(R.drawable.ic_digital_ocean),
        Discord(R.drawable.ic_discord),
        Dropbox(R.drawable.ic_dropbox),
        Facebook(R.drawable.ic_facebook),
        Git(R.drawable.ic_git),
        Github(R.drawable.ic_github),
        Gitlab(R.drawable.ic_gitlab),
        Google(R.drawable.ic_google),
        IFTTT(R.drawable.ic_ifttt),
        Kickstarter(R.drawable.ic_kickstarter),
        LastPass(R.drawable.ic_lastpass),
        Mailgun(R.drawable.ic_mailgun),
        Mastodon(R.drawable.ic_mastodon),
        Microsoft(R.drawable.ic_microsoft),
        NextCloud(R.drawable.ic_nextcloud),
        Origin(R.drawable.ic_origin),
        PayPal(R.drawable.ic_paypal),
        ProtonMail(R.drawable.ic_protonmail),
        RSS(R.drawable.ic_rss),
        Slack(R.drawable.ic_slack),
        Steam(R.drawable.ic_steam),
        Stripe(R.drawable.ic_stripe),
        Twitch(R.drawable.ic_twitch),
        Twitter(R.drawable.ic_twitter),
        Wordpress(R.drawable.ic_wordpress);

        private int resource;
        private AssetType assetType;

        EntryThumbnails(int resource) {
            this.resource = resource;
            this.assetType = AssetType.Vector;
        }

        EntryThumbnails(int resource, AssetType assetType) {
            this.resource = resource;
            this.assetType = assetType;
        }

        public int getResource() {
            return resource;
        }
        public AssetType getAssetType() {
            return assetType;
        }
    }

    public static Bitmap getThumbnailGraphic(Context context, String label, int size, EntryThumbnails thumbnail) {
        if(thumbnail == EntryThumbnails.Default) {
            LetterBitmap letterBitmap = new LetterBitmap(context);
            return letterBitmap.getLetterTile(label, label, size, size);
        }

        try {
            if(thumbnail.getAssetType() == AssetType.Vector) {
                Drawable drawable = context.getResources().getDrawable(thumbnail.getResource());
                Bitmap bitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                drawable.draw(canvas);
                return bitmap;
            } else {
                return BitmapFactory.decodeResource(context.getResources(), thumbnail.getResource());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
    }
}
