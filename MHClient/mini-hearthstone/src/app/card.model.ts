export class Card {
    type: string;
    requiredMana : number;
    damagePoints : number;
    name : string;
    imgurl: string

    constructor(type: string, requiredMana: number, damagePoints: number, name: string, imgurl: string) {
        this.type = type;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.name = name;
        this.imgurl = imgurl;
    }

}
