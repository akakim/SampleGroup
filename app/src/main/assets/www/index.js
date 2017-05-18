   var tempGlobalStr = "( true || false ) && ( true || false )";

function sayhello(){
    alert('hi', document.getElementById('name').value+ '!');
}

function evaluateSample(){


   var testString1 = "true";
   var testString2 = "false";
   var testString3 = testString1 + "||" + testString2;
   var testString4 = testString3 + "&&" + testString3;
   var tempStr = "( true || false ) && ( true || false )";

    var result = evaluate(tempStr)


//    document.write(
//
//     "testString1 : " + testString1 +" eval : " + evaluate(testString1) + "<br>" +
//     "testString2 : " + testString2 +" eval : " + evaluate(testString2) + "<br>" +
//     "testString3 : " + testString3 +" eval : " + evaluate(testString3) + "<br>" +
//     "testString4 : " + testString4 +" eval : " + evaluate(testString4) + "<br>" +
//     "tempStr : " + tempStr + " eval : " + result );

    Android.showToast( "testString1 : " + testString1 +" eval : " + evaluate(testString1) + "<br>" +
                            "testString2 : " + testString2 +" eval : " + evaluate(testString2) + "<br>" +
                            "testString3 : " + testString3 +" eval : " + evaluate(testString3) + "<br>" +
                            "testString4 : " + testString4 +" eval : " + evaluate(testString4) + "<br>" +
                            "tempStr : " + tempStr + " eval : " + result  )
//    document.write(tempStr);
//     document.write(  tempStr );

//    if( tempStr.evaluate){
//        document.write( " evalute true ");
//    }else {
//            document.write( " evalute false ");
//    }
}


function showAndroidDialogg(dialogmsg) {
        Android.showAndroidDialog(dialogmsg);
}
