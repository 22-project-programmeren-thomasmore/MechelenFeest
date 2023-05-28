function initMap(){

    const map = new google.maps.Map(document.getElementById('map'), {
        zoom:12,
        center:{lat:51.02574,lng:4.47762},
    });

    setMarkerWithIcon(map);

}


const festivals = [
    ["Parkpop",51.02454308361425, 4.484819823293105],
    ["Maanrock",51.02846281757062, 4.47997101265373],
    ["De Koer",51.02877370904206, 4.476658615751852],
];


const toilettes = [
    [51.02064981224487, 4.459200462302486],
    [51.017775719967595, 4.460031431982612],
]



function setMarkerWithIcon(map){
    const image = {
        url: "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png",
        size: new google.maps.Size(20,32),
        // origin: new google.maps.Point(0, 0),
        // anchor: new google.maps.Point(0, 32),
    };



    for (let i = 0; i < festivals.length; i++) {
        const festival = festivals[i];


        new google.maps.Marker({
            position: { lat: festival[1], lng: festival[2] },
            map,
            icon: image,
            title: festival[0],
        });

    }


}