package dojo.dp.composite;

class Page extends Block {
}

class Heder extends Block {
}

class Body extends Block {
}

class HeaderTitle extends Block {
}

class UserAvatar extends Block {
}

public class Main {

    public static void main(String[] args) {
        Block page = new Page();
        Block header = new Heder();
        Block body = new Body();
        Block title = new HeaderTitle();
        Block avatar = new UserAvatar();

        page.addBlock(header);
        page.addBlock(body);
        header.addBlock(title);
        header.addBlock(avatar);

        page.render();
    }

}

