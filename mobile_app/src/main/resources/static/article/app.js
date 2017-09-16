'use strict';

var config = {
  homeLink: function(){
    var url = 'http://m.china.com.cn';
    switch(this.langCode){
      case '1001':
        url = 'http://m.china.com.cn';break;
      case '1002':
        url = 'http://m.german.china.org.cn';break;
      case '1003':
        url = 'http://m.china.org.cn';break;
      case '1004':
        url = 'http://m.french.china.org.cn';break;
      case '1005':
        url = 'http://m.arabic.china.org.cn';break;
      case '1006':
        url = 'http://m.korean.china.org.cn';break;
      case '1007':
        url = 'http://m.japanese.china.org.cn';break;
      case '1008':
        url = 'http://m.spanish.china.org.cn';break;
      case '1009':
        url = 'http://m.russian.china.org.cn';break;
      default:
        url = 'http://m.china.com.cn';
    }
    return url;
  },
  pcLink: function(){
    var url = '';
    switch(this.langCode){
      case '1001':
        url = 'http://www.china.com.cn/?f=pad';break;
      case '1002':
        url = 'http://german.china.org.cn/?f=pad';break;
      case '1003':
        url = 'http://www.china.org.cn/?f=pad';break;
      case '1004':
        url = 'http://french.china.org.cn/?f=pad';break;
      case '1005':
        url = 'http://arabic.china.org.cn/?f=pad';break;
      case '1006':
        url = 'http://korean.china.org.cn/?f=pad';break;
      case '1007':
        url = 'http://japanese.china.org.cn/?f=pad';break;
      case '1008':
        url = 'http://spanish.china.org.cn/?f=pad';break;
      case '1009':
        url = 'http://russian.china.org.cn/?f=pad';break;
      default:
        url = 'http://www.china.com.cn/?f=pad';
    }
    return url;
  },
  ua: navigator.userAgent,
  isAndroid: navigator.userAgent.match(/android|linux.*mobile/ig) != null,
  isIos: navigator.userAgent.match(/iphone|ipad|ipod/ig) != null,
  isWeixin: navigator.userAgent.match(/micromessenger/ig) != null,
  langCode: '',
  articleId: '',
  columnId: '',
  columnUrl: '',
  columnName: '',
  addWeixinShare: function(){  //添加微信分享
    if(this.isWeixin){
      console.log('Is weixin, add share.');

      var script = document.createElement("script");
      script.setAttribute("src","http://res.wx.qq.com/open/js/jweixin-1.0.0.js");
      document.getElementsByTagName("head")[0].appendChild(script);

      var script = document.createElement("script");
      script.setAttribute("id",'wx-share-data');
      script.setAttribute("data-title",$('title').text());
      script.setAttribute("data-desc",$('[name="description"]').attr('content') ||'m.china.com.cn');
      script.setAttribute("data-img",'http://m.china.com.cn/images/v2/shareF.png');
      script.setAttribute("src","http://m.china.com.cn/scripts/wxshare.js");
      document.getElementsByTagName("head")[0].appendChild(script);
    }
  },
  addEventsToElements: function(){
    var that = this;

    $('.event-home').click(function(){
      window.location.href = that.homeLink();
    });

    $('.event-top').click(function(){
      window.scrollTo(0,0);
    });

    $('.event-column').click(function(){
      
      window.location.href = that.columnUrl;
    });
    
    $('.event-search').click(function(){
      var val = $('.search-box').val();
      var langUrl = that.pcLink();
      if(val === ''){

      }else{
        window.location.href = 'https://www.baidu.com/baidu?tn=baidu&cl=3&ct=2097152&si='+langUrl+'&word='+val;
      }
    });

    $('.event-pc').click(function(){
      // console.log(that.pcLink());
        window.location.href = that.pcLink(); //+'?f=pad'
    });

    $('.event-download').click(function(){
      if(that.isIos){
        window.open('https://itunes.apple.com/cn/app/zhong-guo-wang-ke-hu-duan/id957292028?mt=8');
      }
      if(that.isAndroid){
        window.location.href = 'http://app.china.com.cn/chinanews/chinanews.apk';
      }
    });
  },
  openApp: function(){
    var that = this;

    if(this.isIos){
      $('#openApp').attr('href','https://s.h5.china.com.cn?articleId='+this.articleId);
    }else if(this.isAndroid){
      var script = document.createElement("script");
      script.setAttribute("id","mlinks");
      script.setAttribute("src","https://static.mlinks.cc/scripts/dist/mlink.min.js");
      document.getElementsByTagName("head")[0].appendChild(script);
      
      $('#mlinks').on('load',function(){
        new Mlink({
          mlink: "https://a.mlinks.cc/AKDt",
          button: document.querySelector('a#openApp'),
          autoLaunchApp : false,
          autoRedirectToDownloadUrl: true,
          downloadWhenUniversalLinkFailed: false,
          inapp : false,
          params: {
              articleId: that.articleId
          }
        });
      });
    }
  },
  infoFormat: function(){
    if(this.langCode === '1003'){ //英语
      var pubtime = $("#pubtime").text().replace(/\s/ig,"");
      var year = pubtime.substring(0,4);
      var month = parseInt(pubtime.substring(5,7))-1;
      var monthArr = ["January","February","March","April","May","June","July","August","September","October","November","December"];
      month = monthArr[month];
      var day = pubtime.substring(8,10).replace(/^0/ig,"");

      $("#pubtime").attr('data-original-date',$("#pubtime").text().replace(/\s/,''));
      $("#pubtime").text(month+" "+day+", "+year);
    }
    if(this.langCode === '1002'){
      $("#pubtime").attr('data-original-date',$("#pubtime").text().replace(/\s/,''));
      $("#pubtime").text($("#pubtime").text().replace(/-/g,'.'));
    }

    //info过长，导致错行处理
    var rootFontSize = window.innerWidth>640?40:window.innerWidth/16;
    if($('.info').height() > rootFontSize*6/10){
      $('.info').css('line-height','.75rem');
      $('#pubtime').prev().css('visibility','hidden');
      $('#pubtime').before('<br>');
    }
    $(".info").css('opacity','1');
  },
  moreInfoDeal: function(){
    if($('.more .list').length === 0){
      $('.more').css('display','none');
      $('.more').next().css('display','none');
    }else{
      var tag = /com\.cn\/wm/.test(location.href)?'wm':/com\.cn\/appshare/.test(location.href)?'appshare':'test';

      $('.more a').each(function(i,t){
        t.href = t.href.replace('appdoc',tag).replace('manager.','');
      });
    }
  },
  adaptAdTestForAa: function(){
    var body = $('body').outerWidth();
    var btn = $('.btn-ad-box').outerWidth();
    var text = $('.text-ad-aa').outerWidth();
    var logo = $('.logo-ad-aa').outerWidth();
    var right = body/16*0.4;

    if( text > body-btn-logo-right ){
      var scale = (body-btn-logo-right)/text;
      // console.log('',scale);
      
      $('.text-ad-aa').css({
        'transform': 'scale3d(' + scale + ',' + scale +',1)',
        '-webkit-transform': 'scale3d(' + scale + ',' + scale +',1)'
      });
    }
  },
  adaptDirectionForAa: function(){
    $('.aa .content p').each(function(){
      // console.log(1);
      if( /[\u4e00-\u9fa5]/ig.test($(this).text()) && $(this).text().match(/[\u4e00-\u9fa5]/ig).length/$(this).text().length >= 0.5){
        $(this).css('direction','ltr');
      }
    });
  },
  addDplusEventForMore: function(){
    if($('#more .list').length>0 && window.dplus && dplus.track){
      $('#more .list a').each(function(){
        $(this).attr('url',$(this).attr('href')).attr('href','javascript:;').click(function(){
          dplus.track("clickRelatedArticle",{
            articleUrl: location.href,
            articleTitle: $('title').text(),
            url: $(this).attr('url'),
            title: $(this).text()
          },function(){
            console.log('d-plus 送达.');
          });

          window.location.href = $(this).attr('url');
        });
      });
    }
  },
  addDplusEventForVideo: function(){
    if($('video').length>0 && window.dplus && dplus.track){
      $('video').on('play',function(){
        dplus.track("playVideo",{
          videoUrl: $(this).attr('src') || $(this).find('source').attr('src')
        },function(){
          console.log('d-plus 送达.');
        });
      });
    }
  },
  addLoadGifForVideo: function(){
    $('video').on('play',function(){
      // console.log('video palyed.');
      $(this).attr('poster','http://m.china.com.cn/images/app/loadVideo.gif');
    });
  },
  init: function(){
    //格式化来源信息
    this.infoFormat();

    //如果没有更多推荐，隐藏它，修改更多推荐使用的模板
    this.moreInfoDeal();

    //调整阿语的广告文字大小，避免出现压住字的现象
    this.adaptAdTestForAa();

    //阿语页面里有中文的情况下，给中文添加'direction':'ltr'样式
    this.adaptDirectionForAa();

    //给所有按钮添加事件
    this.addEventsToElements();

    //给相关推荐的文章添加dplus事件
    this.addDplusEventForMore();

    //给带有视频的文章添加dplus事件
    this.addDplusEventForVideo();

    //给视频添加加载gif
    this.addLoadGifForVideo();

    //添加打开app功能
    this.openApp();

    //添加微信分享
    this.addWeixinShare();

  }
};



