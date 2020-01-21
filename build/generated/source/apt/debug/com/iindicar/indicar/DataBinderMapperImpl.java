package com.iindicar.indicar;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.iindicar.indicar.databinding.ActivityAccountSettingBindingImpl;
import com.iindicar.indicar.databinding.ActivityAlbumBindingImpl;
import com.iindicar.indicar.databinding.ActivityBoardDetailBindingImpl;
import com.iindicar.indicar.databinding.ActivityBoardWriteBindingImpl;
import com.iindicar.indicar.databinding.ActivityCarFilterBindingImpl;
import com.iindicar.indicar.databinding.ActivityCommentBindingImpl;
import com.iindicar.indicar.databinding.ActivityJoinBindingImpl;
import com.iindicar.indicar.databinding.ActivityLanguageBindingImpl;
import com.iindicar.indicar.databinding.ActivityLanguageSettingBindingImpl;
import com.iindicar.indicar.databinding.ActivityLoginBindingImpl;
import com.iindicar.indicar.databinding.ActivityMainBindingImpl;
import com.iindicar.indicar.databinding.ActivityNoticeBindingImpl;
import com.iindicar.indicar.databinding.ActivityNoticeDetailBindingImpl;
import com.iindicar.indicar.databinding.ActivityNotifySettingBindingImpl;
import com.iindicar.indicar.databinding.ActivityShoppingDetailBindingImpl;
import com.iindicar.indicar.databinding.ActivityTuningBindingImpl;
import com.iindicar.indicar.databinding.ActivityTutorialBindingImpl;
import com.iindicar.indicar.databinding.DialogCustomBindingImpl;
import com.iindicar.indicar.databinding.FragmentAccountBindingImpl;
import com.iindicar.indicar.databinding.FragmentBoardListBindingImpl;
import com.iindicar.indicar.databinding.FragmentBoardSearchBindingImpl;
import com.iindicar.indicar.databinding.FragmentCartBindingImpl;
import com.iindicar.indicar.databinding.FragmentCommunityBindingImpl;
import com.iindicar.indicar.databinding.FragmentMainBindingImpl;
import com.iindicar.indicar.databinding.FragmentMenuBindingImpl;
import com.iindicar.indicar.databinding.FragmentProductListBindingImpl;
import com.iindicar.indicar.databinding.FragmentProfileBindingImpl;
import com.iindicar.indicar.databinding.FragmentShoppingBindingImpl;
import com.iindicar.indicar.databinding.FragmentShoppingEngBindingImpl;
import com.iindicar.indicar.databinding.FragmentShoppingHomeBindingImpl;
import com.iindicar.indicar.databinding.FragmentTutorialBindingImpl;
import com.iindicar.indicar.databinding.FragmentWriteContentBindingImpl;
import com.iindicar.indicar.databinding.FragmentWriteFilterBindingImpl;
import com.iindicar.indicar.databinding.HolderAccountSettingItemBindingImpl;
import com.iindicar.indicar.databinding.HolderAlbumItemBindingImpl;
import com.iindicar.indicar.databinding.HolderBoardFilterBindingImpl;
import com.iindicar.indicar.databinding.HolderBoardHeaderBindingImpl;
import com.iindicar.indicar.databinding.HolderBoardItemBindingImpl;
import com.iindicar.indicar.databinding.HolderCarListItemBindingImpl;
import com.iindicar.indicar.databinding.HolderCommentBoxBindingImpl;
import com.iindicar.indicar.databinding.HolderCommentItemBindingImpl;
import com.iindicar.indicar.databinding.HolderFileItemBindingImpl;
import com.iindicar.indicar.databinding.HolderImageBannerItemBindingImpl;
import com.iindicar.indicar.databinding.HolderLanguageSettingItemBindingImpl;
import com.iindicar.indicar.databinding.HolderMenuItemBindingImpl;
import com.iindicar.indicar.databinding.HolderNoticeItemBindingImpl;
import com.iindicar.indicar.databinding.HolderNoticeListItemBindingImpl;
import com.iindicar.indicar.databinding.HolderNotifySettingItemBindingImpl;
import com.iindicar.indicar.databinding.HolderPickedImageBindingImpl;
import com.iindicar.indicar.databinding.HolderShoppingBestItemBindingImpl;
import com.iindicar.indicar.databinding.HolderShoppingItemBindingImpl;
import com.iindicar.indicar.databinding.HolderWriteContentBindingImpl;
import com.iindicar.indicar.databinding.LayoutAccountTabBindingImpl;
import com.iindicar.indicar.databinding.LayoutBoardDetailBindingImpl;
import com.iindicar.indicar.databinding.LayoutTextToolbarBindingImpl;
import com.iindicar.indicar.databinding.LayoutToolbarBindingImpl;
import com.iindicar.indicar.databinding.LayoutWriteFilterItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYACCOUNTSETTING = 1;

  private static final int LAYOUT_ACTIVITYALBUM = 2;

  private static final int LAYOUT_ACTIVITYBOARDDETAIL = 3;

  private static final int LAYOUT_ACTIVITYBOARDWRITE = 4;

  private static final int LAYOUT_ACTIVITYCARFILTER = 5;

  private static final int LAYOUT_ACTIVITYCOMMENT = 6;

  private static final int LAYOUT_ACTIVITYJOIN = 7;

  private static final int LAYOUT_ACTIVITYLANGUAGE = 8;

  private static final int LAYOUT_ACTIVITYLANGUAGESETTING = 9;

  private static final int LAYOUT_ACTIVITYLOGIN = 10;

  private static final int LAYOUT_ACTIVITYMAIN = 11;

  private static final int LAYOUT_ACTIVITYNOTICE = 12;

  private static final int LAYOUT_ACTIVITYNOTICEDETAIL = 13;

  private static final int LAYOUT_ACTIVITYNOTIFYSETTING = 14;

  private static final int LAYOUT_ACTIVITYSHOPPINGDETAIL = 15;

  private static final int LAYOUT_ACTIVITYTUNING = 16;

  private static final int LAYOUT_ACTIVITYTUTORIAL = 17;

  private static final int LAYOUT_DIALOGCUSTOM = 18;

  private static final int LAYOUT_FRAGMENTACCOUNT = 19;

  private static final int LAYOUT_FRAGMENTBOARDLIST = 20;

  private static final int LAYOUT_FRAGMENTBOARDSEARCH = 21;

  private static final int LAYOUT_FRAGMENTCART = 22;

  private static final int LAYOUT_FRAGMENTCOMMUNITY = 23;

  private static final int LAYOUT_FRAGMENTMAIN = 24;

  private static final int LAYOUT_FRAGMENTMENU = 25;

  private static final int LAYOUT_FRAGMENTPRODUCTLIST = 26;

  private static final int LAYOUT_FRAGMENTPROFILE = 27;

  private static final int LAYOUT_FRAGMENTSHOPPING = 28;

  private static final int LAYOUT_FRAGMENTSHOPPINGENG = 29;

  private static final int LAYOUT_FRAGMENTSHOPPINGHOME = 30;

  private static final int LAYOUT_FRAGMENTTUTORIAL = 31;

  private static final int LAYOUT_FRAGMENTWRITECONTENT = 32;

  private static final int LAYOUT_FRAGMENTWRITEFILTER = 33;

  private static final int LAYOUT_HOLDERACCOUNTSETTINGITEM = 34;

  private static final int LAYOUT_HOLDERALBUMITEM = 35;

  private static final int LAYOUT_HOLDERBOARDFILTER = 36;

  private static final int LAYOUT_HOLDERBOARDHEADER = 37;

  private static final int LAYOUT_HOLDERBOARDITEM = 38;

  private static final int LAYOUT_HOLDERCARLISTITEM = 39;

  private static final int LAYOUT_HOLDERCOMMENTBOX = 40;

  private static final int LAYOUT_HOLDERCOMMENTITEM = 41;

  private static final int LAYOUT_HOLDERFILEITEM = 42;

  private static final int LAYOUT_HOLDERIMAGEBANNERITEM = 43;

  private static final int LAYOUT_HOLDERLANGUAGESETTINGITEM = 44;

  private static final int LAYOUT_HOLDERMENUITEM = 45;

  private static final int LAYOUT_HOLDERNOTICEITEM = 46;

  private static final int LAYOUT_HOLDERNOTICELISTITEM = 47;

  private static final int LAYOUT_HOLDERNOTIFYSETTINGITEM = 48;

  private static final int LAYOUT_HOLDERPICKEDIMAGE = 49;

  private static final int LAYOUT_HOLDERSHOPPINGBESTITEM = 50;

  private static final int LAYOUT_HOLDERSHOPPINGITEM = 51;

  private static final int LAYOUT_HOLDERWRITECONTENT = 52;

  private static final int LAYOUT_LAYOUTACCOUNTTAB = 53;

  private static final int LAYOUT_LAYOUTBOARDDETAIL = 54;

  private static final int LAYOUT_LAYOUTTEXTTOOLBAR = 55;

  private static final int LAYOUT_LAYOUTTOOLBAR = 56;

  private static final int LAYOUT_LAYOUTWRITEFILTERITEM = 57;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(57);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_account_setting, LAYOUT_ACTIVITYACCOUNTSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_album, LAYOUT_ACTIVITYALBUM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_board_detail, LAYOUT_ACTIVITYBOARDDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_board_write, LAYOUT_ACTIVITYBOARDWRITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_car_filter, LAYOUT_ACTIVITYCARFILTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_comment, LAYOUT_ACTIVITYCOMMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_join, LAYOUT_ACTIVITYJOIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_language, LAYOUT_ACTIVITYLANGUAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_language_setting, LAYOUT_ACTIVITYLANGUAGESETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_notice, LAYOUT_ACTIVITYNOTICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_notice_detail, LAYOUT_ACTIVITYNOTICEDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_notify_setting, LAYOUT_ACTIVITYNOTIFYSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_shopping_detail, LAYOUT_ACTIVITYSHOPPINGDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_tuning, LAYOUT_ACTIVITYTUNING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.activity_tutorial, LAYOUT_ACTIVITYTUTORIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.dialog_custom, LAYOUT_DIALOGCUSTOM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_account, LAYOUT_FRAGMENTACCOUNT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_board_list, LAYOUT_FRAGMENTBOARDLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_board_search, LAYOUT_FRAGMENTBOARDSEARCH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_cart, LAYOUT_FRAGMENTCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_community, LAYOUT_FRAGMENTCOMMUNITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_main, LAYOUT_FRAGMENTMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_menu, LAYOUT_FRAGMENTMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_product_list, LAYOUT_FRAGMENTPRODUCTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_profile, LAYOUT_FRAGMENTPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_shopping, LAYOUT_FRAGMENTSHOPPING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_shopping_eng, LAYOUT_FRAGMENTSHOPPINGENG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_shopping_home, LAYOUT_FRAGMENTSHOPPINGHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_tutorial, LAYOUT_FRAGMENTTUTORIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_write_content, LAYOUT_FRAGMENTWRITECONTENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.fragment_write_filter, LAYOUT_FRAGMENTWRITEFILTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_account_setting_item, LAYOUT_HOLDERACCOUNTSETTINGITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_album_item, LAYOUT_HOLDERALBUMITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_board_filter, LAYOUT_HOLDERBOARDFILTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_board_header, LAYOUT_HOLDERBOARDHEADER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_board_item, LAYOUT_HOLDERBOARDITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_car_list_item, LAYOUT_HOLDERCARLISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_comment_box, LAYOUT_HOLDERCOMMENTBOX);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_comment_item, LAYOUT_HOLDERCOMMENTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_file_item, LAYOUT_HOLDERFILEITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_image_banner_item, LAYOUT_HOLDERIMAGEBANNERITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_language_setting_item, LAYOUT_HOLDERLANGUAGESETTINGITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_menu_item, LAYOUT_HOLDERMENUITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_notice_item, LAYOUT_HOLDERNOTICEITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_notice_list_item, LAYOUT_HOLDERNOTICELISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_notify_setting_item, LAYOUT_HOLDERNOTIFYSETTINGITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_picked_image, LAYOUT_HOLDERPICKEDIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_shopping_best_item, LAYOUT_HOLDERSHOPPINGBESTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_shopping_item, LAYOUT_HOLDERSHOPPINGITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.holder_write_content, LAYOUT_HOLDERWRITECONTENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.layout_account_tab, LAYOUT_LAYOUTACCOUNTTAB);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.layout_board_detail, LAYOUT_LAYOUTBOARDDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.layout_text_toolbar, LAYOUT_LAYOUTTEXTTOOLBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.layout_toolbar, LAYOUT_LAYOUTTOOLBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.iindicar.indicar.R.layout.layout_write_filter_item, LAYOUT_LAYOUTWRITEFILTERITEM);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYACCOUNTSETTING: {
        if ("layout/activity_account_setting_0".equals(tag)) {
          return new ActivityAccountSettingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_account_setting is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYALBUM: {
        if ("layout/activity_album_0".equals(tag)) {
          return new ActivityAlbumBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_album is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYBOARDDETAIL: {
        if ("layout/activity_board_detail_0".equals(tag)) {
          return new ActivityBoardDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_board_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYBOARDWRITE: {
        if ("layout/activity_board_write_0".equals(tag)) {
          return new ActivityBoardWriteBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_board_write is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCARFILTER: {
        if ("layout/activity_car_filter_0".equals(tag)) {
          return new ActivityCarFilterBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_car_filter is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCOMMENT: {
        if ("layout/activity_comment_0".equals(tag)) {
          return new ActivityCommentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_comment is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYJOIN: {
        if ("layout/activity_join_0".equals(tag)) {
          return new ActivityJoinBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_join is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLANGUAGE: {
        if ("layout/activity_language_0".equals(tag)) {
          return new ActivityLanguageBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_language is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLANGUAGESETTING: {
        if ("layout/activity_language_setting_0".equals(tag)) {
          return new ActivityLanguageSettingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_language_setting is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLOGIN: {
        if ("layout/activity_login_0".equals(tag)) {
          return new ActivityLoginBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMAIN: {
        if ("layout/activity_main_0".equals(tag)) {
          return new ActivityMainBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYNOTICE: {
        if ("layout/activity_notice_0".equals(tag)) {
          return new ActivityNoticeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_notice is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYNOTICEDETAIL: {
        if ("layout/activity_notice_detail_0".equals(tag)) {
          return new ActivityNoticeDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_notice_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYNOTIFYSETTING: {
        if ("layout/activity_notify_setting_0".equals(tag)) {
          return new ActivityNotifySettingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_notify_setting is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSHOPPINGDETAIL: {
        if ("layout/activity_shopping_detail_0".equals(tag)) {
          return new ActivityShoppingDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_shopping_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTUNING: {
        if ("layout/activity_tuning_0".equals(tag)) {
          return new ActivityTuningBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_tuning is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTUTORIAL: {
        if ("layout/activity_tutorial_0".equals(tag)) {
          return new ActivityTutorialBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_tutorial is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGCUSTOM: {
        if ("layout/dialog_custom_0".equals(tag)) {
          return new DialogCustomBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialog_custom is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTACCOUNT: {
        if ("layout/fragment_account_0".equals(tag)) {
          return new FragmentAccountBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_account is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTBOARDLIST: {
        if ("layout/fragment_board_list_0".equals(tag)) {
          return new FragmentBoardListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_board_list is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTBOARDSEARCH: {
        if ("layout/fragment_board_search_0".equals(tag)) {
          return new FragmentBoardSearchBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_board_search is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTCART: {
        if ("layout/fragment_cart_0".equals(tag)) {
          return new FragmentCartBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_cart is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTCOMMUNITY: {
        if ("layout/fragment_community_0".equals(tag)) {
          return new FragmentCommunityBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_community is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTMAIN: {
        if ("layout/fragment_main_0".equals(tag)) {
          return new FragmentMainBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_main is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTMENU: {
        if ("layout/fragment_menu_0".equals(tag)) {
          return new FragmentMenuBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_menu is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTPRODUCTLIST: {
        if ("layout/fragment_product_list_0".equals(tag)) {
          return new FragmentProductListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_product_list is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTPROFILE: {
        if ("layout/fragment_profile_0".equals(tag)) {
          return new FragmentProfileBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_profile is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTSHOPPING: {
        if ("layout/fragment_shopping_0".equals(tag)) {
          return new FragmentShoppingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_shopping is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTSHOPPINGENG: {
        if ("layout/fragment_shopping_eng_0".equals(tag)) {
          return new FragmentShoppingEngBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_shopping_eng is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTSHOPPINGHOME: {
        if ("layout/fragment_shopping_home_0".equals(tag)) {
          return new FragmentShoppingHomeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_shopping_home is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTTUTORIAL: {
        if ("layout/fragment_tutorial_0".equals(tag)) {
          return new FragmentTutorialBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_tutorial is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTWRITECONTENT: {
        if ("layout/fragment_write_content_0".equals(tag)) {
          return new FragmentWriteContentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_write_content is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTWRITEFILTER: {
        if ("layout/fragment_write_filter_0".equals(tag)) {
          return new FragmentWriteFilterBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_write_filter is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERACCOUNTSETTINGITEM: {
        if ("layout/holder_account_setting_item_0".equals(tag)) {
          return new HolderAccountSettingItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_account_setting_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERALBUMITEM: {
        if ("layout/holder_album_item_0".equals(tag)) {
          return new HolderAlbumItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_album_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERBOARDFILTER: {
        if ("layout/holder_board_filter_0".equals(tag)) {
          return new HolderBoardFilterBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_board_filter is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERBOARDHEADER: {
        if ("layout/holder_board_header_0".equals(tag)) {
          return new HolderBoardHeaderBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_board_header is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERBOARDITEM: {
        if ("layout/holder_board_item_0".equals(tag)) {
          return new HolderBoardItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_board_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERCARLISTITEM: {
        if ("layout/holder_car_list_item_0".equals(tag)) {
          return new HolderCarListItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_car_list_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERCOMMENTBOX: {
        if ("layout/holder_comment_box_0".equals(tag)) {
          return new HolderCommentBoxBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_comment_box is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERCOMMENTITEM: {
        if ("layout/holder_comment_item_0".equals(tag)) {
          return new HolderCommentItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_comment_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERFILEITEM: {
        if ("layout/holder_file_item_0".equals(tag)) {
          return new HolderFileItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_file_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERIMAGEBANNERITEM: {
        if ("layout/holder_image_banner_item_0".equals(tag)) {
          return new HolderImageBannerItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_image_banner_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERLANGUAGESETTINGITEM: {
        if ("layout/holder_language_setting_item_0".equals(tag)) {
          return new HolderLanguageSettingItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_language_setting_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERMENUITEM: {
        if ("layout/holder_menu_item_0".equals(tag)) {
          return new HolderMenuItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_menu_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERNOTICEITEM: {
        if ("layout/holder_notice_item_0".equals(tag)) {
          return new HolderNoticeItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_notice_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERNOTICELISTITEM: {
        if ("layout/holder_notice_list_item_0".equals(tag)) {
          return new HolderNoticeListItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_notice_list_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERNOTIFYSETTINGITEM: {
        if ("layout/holder_notify_setting_item_0".equals(tag)) {
          return new HolderNotifySettingItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_notify_setting_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERPICKEDIMAGE: {
        if ("layout/holder_picked_image_0".equals(tag)) {
          return new HolderPickedImageBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_picked_image is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERSHOPPINGBESTITEM: {
        if ("layout/holder_shopping_best_item_0".equals(tag)) {
          return new HolderShoppingBestItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_shopping_best_item is invalid. Received: " + tag);
      }
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_HOLDERSHOPPINGITEM: {
        if ("layout/holder_shopping_item_0".equals(tag)) {
          return new HolderShoppingItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_shopping_item is invalid. Received: " + tag);
      }
      case  LAYOUT_HOLDERWRITECONTENT: {
        if ("layout/holder_write_content_0".equals(tag)) {
          return new HolderWriteContentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for holder_write_content is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTACCOUNTTAB: {
        if ("layout/layout_account_tab_0".equals(tag)) {
          return new LayoutAccountTabBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_account_tab is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTBOARDDETAIL: {
        if ("layout/layout_board_detail_0".equals(tag)) {
          return new LayoutBoardDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_board_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTTEXTTOOLBAR: {
        if ("layout/layout_text_toolbar_0".equals(tag)) {
          return new LayoutTextToolbarBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_text_toolbar is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTTOOLBAR: {
        if ("layout/layout_toolbar_0".equals(tag)) {
          return new LayoutToolbarBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_toolbar is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTWRITEFILTERITEM: {
        if ("layout/layout_write_filter_item_0".equals(tag)) {
          return new LayoutWriteFilterItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_write_filter_item is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(17);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "item");
      sKeys.put(2, "boardType");
      sKeys.put(3, "presenter");
      sKeys.put(4, "activity");
      sKeys.put(5, "index");
      sKeys.put(6, "language");
      sKeys.put(7, "likeCount");
      sKeys.put(8, "likeBoard");
      sKeys.put(9, "mediaId");
      sKeys.put(10, "title");
      sKeys.put(11, "commentCount");
      sKeys.put(12, "fragment");
      sKeys.put(13, "mediaPath");
      sKeys.put(14, "user");
      sKeys.put(15, "selected");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(57);

    static {
      sKeys.put("layout/activity_account_setting_0", com.iindicar.indicar.R.layout.activity_account_setting);
      sKeys.put("layout/activity_album_0", com.iindicar.indicar.R.layout.activity_album);
      sKeys.put("layout/activity_board_detail_0", com.iindicar.indicar.R.layout.activity_board_detail);
      sKeys.put("layout/activity_board_write_0", com.iindicar.indicar.R.layout.activity_board_write);
      sKeys.put("layout/activity_car_filter_0", com.iindicar.indicar.R.layout.activity_car_filter);
      sKeys.put("layout/activity_comment_0", com.iindicar.indicar.R.layout.activity_comment);
      sKeys.put("layout/activity_join_0", com.iindicar.indicar.R.layout.activity_join);
      sKeys.put("layout/activity_language_0", com.iindicar.indicar.R.layout.activity_language);
      sKeys.put("layout/activity_language_setting_0", com.iindicar.indicar.R.layout.activity_language_setting);
      sKeys.put("layout/activity_login_0", com.iindicar.indicar.R.layout.activity_login);
      sKeys.put("layout/activity_main_0", com.iindicar.indicar.R.layout.activity_main);
      sKeys.put("layout/activity_notice_0", com.iindicar.indicar.R.layout.activity_notice);
      sKeys.put("layout/activity_notice_detail_0", com.iindicar.indicar.R.layout.activity_notice_detail);
      sKeys.put("layout/activity_notify_setting_0", com.iindicar.indicar.R.layout.activity_notify_setting);
      sKeys.put("layout/activity_shopping_detail_0", com.iindicar.indicar.R.layout.activity_shopping_detail);
      sKeys.put("layout/activity_tuning_0", com.iindicar.indicar.R.layout.activity_tuning);
      sKeys.put("layout/activity_tutorial_0", com.iindicar.indicar.R.layout.activity_tutorial);
      sKeys.put("layout/dialog_custom_0", com.iindicar.indicar.R.layout.dialog_custom);
      sKeys.put("layout/fragment_account_0", com.iindicar.indicar.R.layout.fragment_account);
      sKeys.put("layout/fragment_board_list_0", com.iindicar.indicar.R.layout.fragment_board_list);
      sKeys.put("layout/fragment_board_search_0", com.iindicar.indicar.R.layout.fragment_board_search);
      sKeys.put("layout/fragment_cart_0", com.iindicar.indicar.R.layout.fragment_cart);
      sKeys.put("layout/fragment_community_0", com.iindicar.indicar.R.layout.fragment_community);
      sKeys.put("layout/fragment_main_0", com.iindicar.indicar.R.layout.fragment_main);
      sKeys.put("layout/fragment_menu_0", com.iindicar.indicar.R.layout.fragment_menu);
      sKeys.put("layout/fragment_product_list_0", com.iindicar.indicar.R.layout.fragment_product_list);
      sKeys.put("layout/fragment_profile_0", com.iindicar.indicar.R.layout.fragment_profile);
      sKeys.put("layout/fragment_shopping_0", com.iindicar.indicar.R.layout.fragment_shopping);
      sKeys.put("layout/fragment_shopping_eng_0", com.iindicar.indicar.R.layout.fragment_shopping_eng);
      sKeys.put("layout/fragment_shopping_home_0", com.iindicar.indicar.R.layout.fragment_shopping_home);
      sKeys.put("layout/fragment_tutorial_0", com.iindicar.indicar.R.layout.fragment_tutorial);
      sKeys.put("layout/fragment_write_content_0", com.iindicar.indicar.R.layout.fragment_write_content);
      sKeys.put("layout/fragment_write_filter_0", com.iindicar.indicar.R.layout.fragment_write_filter);
      sKeys.put("layout/holder_account_setting_item_0", com.iindicar.indicar.R.layout.holder_account_setting_item);
      sKeys.put("layout/holder_album_item_0", com.iindicar.indicar.R.layout.holder_album_item);
      sKeys.put("layout/holder_board_filter_0", com.iindicar.indicar.R.layout.holder_board_filter);
      sKeys.put("layout/holder_board_header_0", com.iindicar.indicar.R.layout.holder_board_header);
      sKeys.put("layout/holder_board_item_0", com.iindicar.indicar.R.layout.holder_board_item);
      sKeys.put("layout/holder_car_list_item_0", com.iindicar.indicar.R.layout.holder_car_list_item);
      sKeys.put("layout/holder_comment_box_0", com.iindicar.indicar.R.layout.holder_comment_box);
      sKeys.put("layout/holder_comment_item_0", com.iindicar.indicar.R.layout.holder_comment_item);
      sKeys.put("layout/holder_file_item_0", com.iindicar.indicar.R.layout.holder_file_item);
      sKeys.put("layout/holder_image_banner_item_0", com.iindicar.indicar.R.layout.holder_image_banner_item);
      sKeys.put("layout/holder_language_setting_item_0", com.iindicar.indicar.R.layout.holder_language_setting_item);
      sKeys.put("layout/holder_menu_item_0", com.iindicar.indicar.R.layout.holder_menu_item);
      sKeys.put("layout/holder_notice_item_0", com.iindicar.indicar.R.layout.holder_notice_item);
      sKeys.put("layout/holder_notice_list_item_0", com.iindicar.indicar.R.layout.holder_notice_list_item);
      sKeys.put("layout/holder_notify_setting_item_0", com.iindicar.indicar.R.layout.holder_notify_setting_item);
      sKeys.put("layout/holder_picked_image_0", com.iindicar.indicar.R.layout.holder_picked_image);
      sKeys.put("layout/holder_shopping_best_item_0", com.iindicar.indicar.R.layout.holder_shopping_best_item);
      sKeys.put("layout/holder_shopping_item_0", com.iindicar.indicar.R.layout.holder_shopping_item);
      sKeys.put("layout/holder_write_content_0", com.iindicar.indicar.R.layout.holder_write_content);
      sKeys.put("layout/layout_account_tab_0", com.iindicar.indicar.R.layout.layout_account_tab);
      sKeys.put("layout/layout_board_detail_0", com.iindicar.indicar.R.layout.layout_board_detail);
      sKeys.put("layout/layout_text_toolbar_0", com.iindicar.indicar.R.layout.layout_text_toolbar);
      sKeys.put("layout/layout_toolbar_0", com.iindicar.indicar.R.layout.layout_toolbar);
      sKeys.put("layout/layout_write_filter_item_0", com.iindicar.indicar.R.layout.layout_write_filter_item);
    }
  }
}
