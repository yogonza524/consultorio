function Pieza(id, width){

        this.s = Snap(id);

        this.colorHover = "#B9D1D6";
        this.colorBlur = "rgb(236, 240, 241)";

        //Methods
        var hoverPath = function(path, colorHover, colorBlur, hover){
                this.colorHover = colorHover;
                this.colorBlur = colorBlur;

                path
                    .mouseover(function() {
                            this.animate({
                                fill: colorHover
                            }, 200);
                            if (hover !== null && hover !== 'undefined') {
                                hover();
                            }
                        })
                    .mouseout(function() {
                            this.animate({
                                fill: colorBlur
                            }, 200);
                        });
        }

        this.hoverTop = function(colorHover, hover){
                var colorBlur = this.top.attr("fill");
                hoverPath(this.top, colorHover, colorBlur, hover);
        }

        this.hoverLeft = function(colorHover, hover){
                var colorBlur = this.left.attr("fill");
                hoverPath(this.left, colorHover, colorBlur, hover);
        }

        this.hoverRight = function(colorHover, hover){
                var colorBlur = this.right.attr("fill");
                hoverPath(this.right, colorHover, colorBlur, hover);
        }

        this.hoverCenter = function(colorHover, hover){
                var colorBlur = this.center.attr("fill");
                hoverPath(this.center, colorHover, colorBlur, hover);
        }

        this.hoverBottom = function(colorHover, hover){
                var colorBlur = this.bottom.attr("fill");
                hoverPath(this.bottom, colorHover, colorBlur, hover);
        }

        this.paint = function(){

                var svg = document.getElementById(id.split("#").pop());
                if (svg == null || svg == 'undefined') { console.warn("SVG tag with id " + id + " not found"); return;}

                //Compute the size
                var seventyFive = Math.floor(width * 0.75);
                var twentyFive = Math.floor(width * 0.25);

                this.s.attr({
                  fill: "rgb(236, 240, 241)",
                  stroke: "#1f2c39",
                  strokeWidth: 3
                });

                //Create top, left, center, right, bottom
                this.top = this.s.path("M0 0 L" + width + " 0 L" + seventyFive + " " + twentyFive + " L" + twentyFive +" " + twentyFive + " Z");
                this.right = this.s.path("M" + width + " 0 L" + seventyFive + " " + twentyFive + " L" + seventyFive + " " + seventyFive + " L" + width + " " + width + " Z");
                this.bottom = this.s.path("M0 " + width + " L" + (seventyFive + 1) + " " + (twentyFive + 1) + " L"+ (seventyFive + 1) + " " + (seventyFive + 1) + " L" + width + " " + width + " Z");
                this.left = this.s.path("M0 0 L" + twentyFive + " " + twentyFive + " L" + twentyFive + " " + seventyFive + " L0 " + width + " Z");
                this.center = this.s.path("M" + twentyFive + " " + twentyFive + " L"+ seventyFive + " " +twentyFive + " L" + seventyFive + " " + seventyFive + " L" + twentyFive + " " + seventyFive + " Z");
        }


        this.getTop = function(){
                return this.top;
        }

        this.getCenter = function(){
                return this.center;
        }

        this.getLeft = function(){
                return this.left;
        }

        this.getRight = function(){
                return this.right;
        }

        this.getBottom = function(){
                return this.bottom;
        }

        this.normalColor = "rgb(236, 240, 241)";
        this.clickedColor = "#99ceff";

        this.setNormalColor = function(color){
                this.normalColor = color;
        }

        this.setClickedColor = function(color){
                this.clickedColor = color;
        }

        this.getNormalColor = function(){
                return this.normalColor;
        }

        this.getClickedColor = function(){
                return this.clickedColor;
        }

        this.click = function(callback){
                this.svg.click(callback);
        }

        this.clickTop = function(callback){
                this.top.click(callback);
        }

        this.clickCenter = function(callback){
                this.center.click(callback);
        }

        this.clickLeft = function(callback){
                this.left.click(callback);
        }

        this.clickBottom = function(callback){
                this.bottom.click(callback);
        }

        this.clickRight = function(callback){
                this.right.click(callback);
        }

        this.colorLeft = function(color){
                this.left.attr("fill", color);
                hoverPath(this.left,this.colorHover, color);
        }

        this.colorTop = function(color){
                this.top.attr("fill", color);
                hoverPath(this.top,this.colorHover, color);
        }

        this.colorBottom = function(color){
                this.bottom.attr("fill", color);
                hoverPath(this.bottom,this.colorHover, color);
        }

        this.colorRight = function(color){
                this.right.attr("fill", color);
                hoverPath(this.right,this.colorHover, color);
        }

        this.colorCenter = function(color){
                this.center.attr("fill", color);
                hoverPath(this.center,this.colorHover, color);
        }

        //Draw
        this.paint();
}