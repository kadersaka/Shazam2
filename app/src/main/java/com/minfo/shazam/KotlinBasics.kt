package com.minfo.shazam

fun main(){
    var season = 3
    when(season){
        1->println("One")
        2->println("Two")
        2-> {
            println("Three")

        }
        else->{
            println("Error")
        }
    }
    
    
    var month =3


    when(month){
        in 3..5 -> println("Spring")
        in 6..8 -> println("Summer")
        in 9..11 -> println("Fall")
        12, 1, 2 -> println("kader")
        else->{
            println("Error")
        }
    }

    var x:Any = 13.37

    when(x){
        is Int -> println("$x is an Int")
        is Double -> println("$x is an double")
        is String -> println("$x is a String")
        is Float -> println("$x is a Float")
        !is Double -> println("$x is not an double")
        else -> println("$x is none of the above")
    }

for(num in 1..10){
    print("$num-")
}

for(i in 1 until 10){
    print("$i-")
    if(i/2 == 5){
        break
//        continue
    }
}
println('_')
for(i in 10 downTo 1){
    print("$i-")
}
println('_')
for(i in 10 downTo 1 step 2){
    print("$i-")
}



}