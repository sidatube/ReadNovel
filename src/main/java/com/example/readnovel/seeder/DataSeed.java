package com.example.readnovel.seeder;

import com.example.readnovel.constant.AccountStatusEnum;
import com.example.readnovel.constant.TranslationStatus;
import com.example.readnovel.constant.TypeOfStory;
import com.example.readnovel.entity.*;
import com.example.readnovel.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeed implements CommandLineRunner {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleTeamRepository roleTeamRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    TranslationTeamRepository translationTeamRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    NovelRepository novelRepository;
    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    ChapterRepository chapterRepository;

    @Override
    public void run(String... args) throws Exception {
        addRole();
        addTeamRole();
        addUser();
        addAuthor();
        addArtist();
        addType();
        addTransTeam();
        addNovel();
        addVol();
        addChapter();
    }

    private void addChapter() {
        if (chapterRepository.findAll().isEmpty()) {
            Volume arafoo = volumeRepository.findById("VOL-1").get();
            Volume imotou = volumeRepository.findById("VOL-2").get();
            Volume oregairu = volumeRepository.findById("VOL-3").get();
            Chapter imotouChap = Chapter.builder()
                    .volume(imotou)
                    .numberTitle("Chap 1")
                    .number(1)
                    .title("Tên tiểu thuyết gia cuồng em gái")
                    .content("<p id=\"1\">“Anh ơi, dậy đi nào, dậy đi, dậyyy…”</p>" +
                            "<p id=\"2\">Khi tôi mở mắt để đáp lại giọng nói ấy, thứ chào đón tôi không gì khác ngoài hình ảnh của Alice, đang hoàn toàn khỏa thân.</p>" +
                            "<p id=\"3\">Alice là em gái của tôi. Năm nay em ấy vừa tròn 14 tuổi nhưng mái tóc vàng suôn mượt cùng đôi mắt đỏ thẫm màu thạch anh của em ấy lại để lại một ấn tượng khó quên. Em ấy thật xinh đẹp, không ai có thể phủ nhận điều đó.</p>" +
                            "<p id=\"4\">“Mngh…, chào buổi sáng, Alice,”</p>" +
                            "<p id=\"5\">Tôi ấp úng đáp, vẫn còn đang ngái ngủ. Em ấy chỉ cười khúc khích để đáp lại tôi.</p>" +
                            "<p id=\"6\">“Anh hai, sáng nay anh ngủ nướng thật đó! Với một người anh trai ngái ngủ như vậy, đây, hãy nhận lấy…”</p>" +
                            "<p id=\"7\">Em ấy nhanh chóng áp mặt lại gần… và trao cho tôi một nụ hôn.</p>" +
                            "<p id=\"8\">“…!”</p>" +
                            "<p id=\"9\">Đôi môi mềm mại của Alice hạ cánh trên môi của tôi, xua đi mọi cơn buồn ngủ đang đeo bám.</p>" +
                            "<p id=\"10\">“Đã tỉnh táo hơn chưa?”</p>" +
                            "<p id=\"11\">Em ấy dần tách ra, nở một nụ cười ranh mãnh. Đôi gò má dần ửng hồng.</p>" +
                            "<p id=\"12\">“Hôm nay Alice đã nấu cho anh một bữa sáng siêu-cấp-đặc-biệt đấy! Hãy ăn trước khi chúng nguội nào!”</p>" +
                            "<p id=\"13\">“Oh! Anh xuống ngay,” tôi đáp.</p>" +
                            "<p id=\"14\">Em ấy gật đầu, trông thật thỏa mãn trong ‘bộ đồ bằng da’ và rời khỏi phòng. Mông em ấy, mềm mại và căng tròn như một con tôm vừa mới lột vỏ, chúng lắc lư qua lại nhịp nhàng theo từng bước chân rời khỏi phòng.</p>" +
                            "<p id=\"15\">Tôi luôn khởi đầu ngày mới của mình như thế này đã hàng trăm lần, nhưng tôi chưa bao giờ thấy chán cả. Tôi rời khỏi giường trong khi suy ngẫm về những niềm vui thuần khiết này, mong chờ được tận hưởng bữa ăn ngon lành mà em ấy đã chuẩn bị. Kế đó, tôi rửa mặt bằng nước ấm vừa mới ngâm mình của em gái, lau mặt bằng áo ngực vừa mới cởi vẫn còn nóng hổi của em ấy và tiến đến phòng ăn. Thật bất ngờ, Yoshiko, người mà theo tôi nhớ vừa mới chết ngày hôm qua, đang ở đây.</p>" +
                            "<p id=\"16\">“Nào, anh hai,” Alice, vẫn đang khỏa thân, nở một nụ cười tan chảy trái tim, “ăn ngay khi còn nóng nào!”</p>" +
                            "<p id=\"17\">“Cảm ơn em!”</p>" +
                            "<p id=\"18\">Món cơm chiên trứng của em ấy vẫn tuyệt vời như mọi khi. Cả món sữa đi kèm cũng vậy, mùi vị này hoàn toàn bỏ xa những loại sữa mà tôi từng uống. Trứng do chính em ấy đẻ ra, sữa tươi vừa mới vắt của em ấy, một sự kết hợp tối thượng.</p>" +
                            "<p id=\"19\">“Nàyy, anh hai, có tương cà dính lên mặt anh kìa! Ooh, meeowww, để xem… Um, có thứ gì để lau không nhỉ, thứ gì đó để lau…”</p>" +
                            "<p id=\"20\">Khéo léo mở ra một cổng không gian thông đến chiều không gian khác, Alice lấy ra một chiếc quần lót bông ấm áp và chấm vào một bên khóe miệng tôi. Mùi hương quyến rũ đến từ không gian khác của Alice phảng phất nơi cánh mũi, làm chúng trở hưng phấn và kích thích mong muốn được ăn trong tôi. Ước gì tôi có thể ăn những cái quần lót này. Nhoàm nhoàm… A! Tôi thực sự đã nhai chúng. Nhồm nhoàm, nhồm nhoàm… Chúng mới ngon làm sao.</p>" +
                            "<p id=\"21\">Sau đó, tôi tiếp tục bữa sáng với áo ngực của em gái. Alice đáp lại bằng một cái bĩu môi.</p>" +
                            "<p id=\"22\">“Nàyy, anh haiii… Nếu anh thật sự muốn nếm thử chiếc quần lót đáng yêu của em như vậy, em sẽ chuẩn bị cho anh một cái mới vẫn còn nóng hổi cùng với sữa tươi nguyên chất của mình nhé! Tee-hee! ©”</p>" +
                            "<p id=\"23\">“Thật ư! Anh không thể đợi tới lúc được thưởng thức thứ đồ lót tươi ngon, nóng hổi, vừa mới ra lò của em!”</p>" +
                            "<p id=\"24\">-------------------------</p>" +
                            "<p id=\"25\">“Cái quái gì đây?!”</p>" +
                            "<p id=\"26\">“Hả! Có... có vấn đề gì à?’</p>" +
                            "<p id=\"27\">Itsuki lập tức đứng thẳng dậy khi Toki đập bản thảo của cậu xuống bàn và hét vào mặt cậu.</p>" +
                            "<p id=\"28\">“Cậu nghĩ cái quái gì vậy?... Cá-cái thế giới quan của cậu thật sự điên rồ… Tôi cảm thấy như bản thân vừa đi vào một cái bãi rác vậy!” Toki vừa trừng mắt nói vừa cố gắng điều chỉnh nhịp thở của mình.</p>" +
                            "<p id=\"29\">Itsuki khoanh tay lại, nở một nụ cười như vừa đánh bại cả thế giới.</p>" +
                            "<p id=\"30\">“Heh, có vẻ như lại có thêm một tên nữa khuất phục trước cái thế giới tuyệt vời của tôi rồi nhỉ?”</p>" +
                            "<p id=\"31\">“Cậu… cái tên điên khùng này…” Toki đáp lại với khuôn mặt căng như dây đàn.</p>" +
                            "<p id=\"32\">Itsuki – cụ thể, Itsuki Hashima – một tiểu thuyết gia, 20 tuổi, có chút nhỏ con và lanh lợi hơn so với độ tuổi. Cặp mắt sắc bén của cậu tạo nên một vẻ ngoài trông như một nhân vật phản diện nào đó, song trên khuôn mặt vẫn còn đọng lại những nét ngây thơ đúng độ tuổi – dù cho cậu đang nhìn chằm chằm vào Toki một cách trơ trẽn, rõ ràng là cậu đang muốn gây hấn. Itsuki Hashima là tên thật của cậu; cậu không chọn cho mình một bút danh nào khác giống như cách những nhà văn cùng thể loại với cậu vẫn hay làm.</p>" +
                            "<p id=\"33\">Trong khi đó, Kenjiro Toki, biên tập viên của Itsuki và là một người đàn ông trông khá nóng tính, hiện 26 tuổi, mang kính và vận một bộ quần áo công sở. Anh ấy và Itsuki đang họp bàn về kịch bản. Họ thường liên lạc với nhau thông qua tin nhắn và điện thoại, nhưng mỗi khi có thể, Itsuki muốn gặp trực tiếp Toki và để anh đọc trực tiếp bản in cứng[note43457] từ bản thảo của cậu. Cậu nghĩ việc đọc trực tiếp như vậy giúp cậu có thể đánh giá chuẩn chỉ hơn về bản thảo của mình thông qua những phản ứng chân thực của biên tập viên.</p>" +
                            "<p id=\"34\">Hôm nay họ đang ở trong căn hộ của Itsuki – một địa điểm gặp mặt quen thuộc, bởi nó chỉ cách nơi làm việc của Toki khoảng chừng 5 phút đi bộ.</p>" +
                            "<p id=\"35\">“… Rồi, để chắc chắn rằng chúng ta vẫn đang cùng tần số,” Toki đánh tiếng, giọng của anh trở nên yếu đi bởi sự ngán ngẫm, “đây là bản thảo đề xuất cho chương 2 của ‘Thợ săn quỷ ở xứ Scarlet’?”</p>" +
                            "<p id=\"36\">“Đúng vậy.”</p>" +
                            "<p id=\"37\">Itsuki gật đầu cái rụp. Hành động này chỉ khiến Toki ngày càng trở nên nhăn nhó.</p>" +
                            "<p id=\"38\">“…Vậy, không phải là nó hơi dị à? Bởi vì theo cốt truyện, dường như chương 2 sẽ bắt đầu bằng cảnh anh hùng bất ngờ trước sự xuất hiện của nữ chính tại bữa sáng, dù cô đã hy sinh tính mạng để bảo vệ cậu khỏi đòn tấn công của một tên quỷ ở cuối chương 1…đúng không?”</p>" +
                            "<p id=\"39\">“Vâng. Tôi đã bám rất sát vào cốt truyện đó mà. Tôi nghĩ nó thật sự hoàn hảo đấy.”</p>" +
                            "<p id=\"40\">Toki đang đề cập đến một sơ đồ viết tay về cốt truyện mà cả hai người đã cùng nhau nghĩ ra trước đây.</p>" +
                            "<p id=\"41\">“Theo sát cốt truyện này á?”</p>" +
                            "<p id=\"42\">Itsuki hơi cau mày lại khi Toki đập tay xuống bàn.</p>" +
                            "<p id=\"43\">“Gì chứ? Anh hùng đã tỏ ra bất ngờ mà, đúng không? Cô gái đã ngã xuống trước mặt anh ấy ngày hôm qua, và ngay bậy giờ lại xuất hiện ở đây… Um, cô ấy tên gì ấy nhỉ?”</p>" +
                            "<p id=\"44\">“Sao cậu lại có thể quên được tên của nữ chính chứ! Cô ấy là Yoshiko, nghe chưa? Yoshiko! Cậu không nghĩ đó là một cái tên xinh xắn à? Đó thật sự là một cái tên tuyệt vời dành cho một cô gái luôn chiến đấu đơn độc, thầm lặng, ẩn mình trong bóng tối của vương quốc để chống lại lũ quỷ! …Được rồi, ý tôi ở đây là cậu đã cố gắng nhồi nhét cô ấy vào đoạn này để làm anh hùng thấy sốc. Là do tôi đã hoàn toàn bỏ qua đoạn đó, nhưng mà…”</p>" +
                            "<p id=\"45\">“Này, anh biên tập à”</p>" +
                            "<p id=\"46\">Itsuki cố tình thở ra một hơi</p>" +
                            "<p id=\"47\">“Anh không thấy cay cú khi anh đã viết một thứ rõ như ban ngày trong câu chuyện của mình rồi, nhưng người đọc lại quên mất hay thậm chí là bỏ qua chúng, sau đó họ lại quay ra chỉ trích anh kiểu như ‘tại sao chi tiết này lại không được đề cập?’ hay ‘cái kiểu truyện gì thế này?’ à?</p>" +
                            "<p id=\"48\">“Đừng có nói cái kiểu như tôi là người xấu như vậy!” Toki lên giọng đáp trả, rồi lại hít một hơi thật sâu để trấn tĩnh bản thân. “Việc nhồi nhét Yoshiko ở đoạn này là một chuyện, nhưng vấn đề chính tôi muốn nói ở đây không phải điều đó.”</p>" +
                            "<p id=\"49\">“Ý anh là gì?”</p>" +
                            "<p id=\"50\">Toki gõ gõ ngón giữa vào bản in trước mặt, “Vấn đề chính ở đây, nhân vật nữ mới xuất hiện tên Alice này là ai vậy? Tôi không nhớ là mình đã được nghe thông tin nào về cô ta cả!”</p>" +
                            "<p id=\"51\">“Cô ấy là em gái của anh hùng. Tôi nhớ là đã viết nó ở phần mô tả nhân vật của anh hùng rồi mà? Anh hùng có một đứa em gái.”</p>" +
                            "<p id=\"52\">“Đúng là cậu đã viết như vậy! Nhưng cậu lại chẳng thêm bất cứ thông tin chi tiết nào về nhân vật này cả, tôi nghĩ cô ta chỉ là một nhân vật thứ cấp. Không một lời giải thích… và giờ cậu cho tôi xem cái thể loại kinh khủng này…!”</p>" +
                            "<p id=\"53\">“Nó đẹp kinh khủng luôn đúng không? Tôi cũng nghĩ vậy đấy!”</p>" +
                            "<p id=\"54\">“Không phải như vậy, thằng ngu này! Tuy đó là sở thích của cậu… Ý tôi là, việc cậu để chế độ mặc định của cô ta là khỏa thân đã đủ quái dị rồi, nhưng còn những chuyện điên khùng hơn thế nữa xảy ra, bây giờ tôi chả thể hiểu nổi cái đống lộn xộn này là gì cả! Anh hùng đi ngửi mùi áo ngực? Anh hùng đi ăn một cái quần lót theo đúng nghĩa đen?... Cậu viết ra cái thể loại anh hùng củ chuối gì thế hả? Cậu có chắc là mình không viết nhầm ‘bánh kếp’ thành ‘quần lót’ không đấy? Hay gì đó đại loại thế…”</p>" +
                            "<p id=\"55\">“Này, đừng hỏi ngớ ngấn thế chứ. Anh nghĩ là tôi sẽ phạm phải một cái sai làm nghiệp dư vậy à?”</p>" +
                            "<p id=\"56\">“Được rồi, cho là cậu không nhầm đi… Thế cái được gọi là ‘sữa’ của Alice ở đây là cái quái gì vậy?”</p>" +
                            "<p id=\"57\">“Thì đúng như tôi viết đấy thôi. Đó là sữa lấy từ ngực của Alice. Cực kỳ phì nhiêu.”</p>" +
                            "<p id=\"58\">“Vậy còn mấy quả trứng…”</p>" +
                            "<p id=\"59\">“Ừm, cô ấy đẻ ra chúng. Hm, kiểu như, chúng bổ dưỡng gấp nghìn lần so với trứng cá muối ấy, à, không phải là tôi thích trứng cá muối đâu nhé.”</p>" +
                            "<p id=\"60\">“Ha-ha-ha… Tất cả mấy thứ này thật quá mức điên khùng! Còn cậu, chủ nhân của cái mớ kiệt tác VL chim én này, mới thật sự là thứ có vấn đề nhất đấy! Tôi cứ nghĩ anh hùng chỉ là một học sinh trung học bình thường xuất thân từ một gia đình kiểu mẫu thôi đấy! Cái màn thể hiện quá mức củ chuối này sẽ đánh bay thứ được gọi là ‘Dòng máu của thợ săn quỷ’ cùng mấy thứ khác khỏi đầu độc giả đấy!”</p>" +
                            "<p id=\"61\">Một tràng liên thanh như vậy bắn tới khiến cho Itsuki khẽ cau mày.</p>" +
                            "<p id=\"62\">“Hmm… Được rồi, nếu anh đã nói vậy thì có lẽ mấy chi tiết liên quan đến sữa-và- trứng đã đi hơi xa một chút rồi… Mà anh biết đấy, tôi đã phải hình dung rất nhiều cảnh chiến đấu siêu nhiên rồi, tôi chỉ muốn trốn tránh thực tại một tý với những ảo tưởng của tuổi trẻ thôi mà…”</p>" +
                            "<p id=\"63\">“Một chút thôi á…? Cậu nghiêm túc…?” Toki đã giận đến mức run run người.</p>" +
                            "<p id=\"64\">“Anh có thể bắt gặp những thứ này trong mấy cuốn sách giống vậy mà. Kiểu như, cha mẹ của anh hùng là những mạo hiểm giả nổi tiếng, hay họ là truyền nhân của một môn phái cổ truyền nào đó và anh hùng được thừa hưởng những tinh hoa từ họ.”</p>" +
                            "<p id=\"65\">“Rồi, bây giờ cậu muốn nói rằng cái nhân vật điên khùng có thể đẻ trứng, và cả cái việc nhai nhồm nhoàm quần lót này đều liên quan đến cốt truyện cơ bản của một bộ truyện tranh anh hùng?</p>" +
                            "<p id=\"66\">Có thể trông thấy khói đang bốc ra từ đầu của Toki lúc này.</p>" +
                            "<p id=\"67\">“… Vâng,” Itsuki rón rén lên tiếng, “Nếu anh có một đứa em gái, và nếu em ấy ngâm bồn từ trước, thì hiển nhiên là phải rửa mặt bằng nước ngâm còn ấm em ấy vừa mới sử dụng còn gì?”</p>" +
                            "<p id=\"68\">“Cậu gọi điều đó là hiển nhiên á?! Tên cuồng em gái chết tiệt!”</p>" +
                            "<p id=\"69\">Toki hét lên bằng tất cả sức lực của mình.</p>" +
                            "<p id=\"70\">***</p>" +
                            "<p id=\"71\">Itsuki Hashima ra mắt lần đầu vào năm 16 tuổi sau khi giành được giải thưởng dành cho những tác giả trẻ. Trong những năm tiếp theo, cậu đã xuất bản tổng cộng 20 cuốn tiểu thuyết khác nhau, trong đó bao gồm 5 bộ truyện ngắn một tập và 3 bộ truyện dài kỳ.</p>" +
                            "<p id=\"72\">Mặc dù cậu đã bắt đầu viết tiểu thuyết từ trước khi trở thành dân chuyên, nhưng con số 20 cuốn chỉ trong thời gian 3 năm thật sự ấn tượng – và cả chất lượng tiểu thuyết cậu viết ra vẫn luôn được đảm bảo, không có gì lạ khi cậu đã gầy dựng một lượng độc giả hâm mộ riêng cho mình. Thậm chí có vài tựa sách của cậu đã lọt vào top 10 sách bán chạy nhất được phát hành bởi Oricon, một thương hiệu nổi tiếng ở Nhật Bản. Không ngoa khi nói rằng cậu thật sự đang trong đà thăng tiến rực rỡ của sự nghiệp.</p>" +
                            "<p id=\"73\">Khi nói đến tốc độ viết lách, trí tưởng tượng, khả năng xây dựng một câu chuyện logic hay những nhân vật hấp dẫn, Itsuki Hashima đã có cho mình mọi thứ để có thể trưởng thành và phát triển trên con đường của một tiểu thuyết gia nổi tiếng – dù có là vậy đi nữa, tất cả cuốn sách cậu đã bán đều ở mức độ “Eh~” nhất định.</p>" +
                            "<p id=\"74\">Mọi câu chuyện của cậu đều theo một mô tuýp đã được đóng khung, nữ chính luôn luôn là nhân vật mang tên “em gái”.</p>" +
                            "<p id=\"75\">Đây là một kiểu ý tưởng quen thuộc ở dòng tiểu thuyết này, và nó cũng có thể thật sự trở nên nổi tiếng, nhưng cái cách Itsuki cố chấp hình mẫu của nhân vật nữ chính chắc chắn đã khiến cho vài đọc giả phải tròn mắt mà thốt lên, “Lại nữa à…” Không dừng lại ở đó, Itsuki luôn nỗ lực phát triển hình mẫu em gái của riêng mình để không bị trùng lặp với những cuốn tiểu thuyết khác, khiến cho nhân vật này ngày càng hành xử táo bạo hơn qua từng tập truyện. Và đến khi nam chính bắt đầu nói về em gái mình theo cái cách không thể nào điên khùng hơn nữa, việc độc giả dần quay lưng lại với cậu chỉ là vấn đề thời gian.</p>" +
                            "<p id=\"76\">Nhận ra việc Itsuki cần có sự thay đổi trong phong cách để có thể phát triển bản thân, Toki đã buộc cậu sáng tác ‘Thợ săn quỷ ở xứ Scarlet’. Và rồi giờ anh phát hiện ra rằng, Itsuki lại vừa tạo ra thêm một con quái vật gắn mác em gái mới.</p>" +
                            "<p id=\"77\">“Ugh… Cái thằng ngu phát rồ vì em gái này…”</p>" +
                            "<p id=\"78\">Toki đang trên đường trở về văn phòng từ nhà của Itsuki. Anh thở dài một hơi, phả ra làn khói trắng có thể trông thấy được vào tiết trời trong lành của tháng một.</p>" +
                            "<p id=\"79\">“A! Chào anh, Toki!”</p>" +
                            "<p id=\"80\">Người vừa chào đón anh là một thiếu niên nhỏ nhắn khoác trên mình áo gió cùng chiếc túi từ cừa hàng tiện lợi đang xách ở một bên tay.</p>" +
                            "<p id=\"81\">“Oh, chào em, Chihiro,” Toki đáp lời. “Anh vừa mới thảo luận ý tưởng với anh trai của em xong và đang trên đường về.”</p>" +
                            "<p id=\"82\">“Vậy à? Cảm ơn anh đã luôn chăm sóc anh trai của em.”</p>" +
                            "<p id=\"83\">“Không có gì. Cậu ấy cũng giúp anh nhiều mà.”</p>" +
                            "<p id=\"84\">Thiếu niên ấy là Chihiro Hashima, em trai của Itsuki. Cậu đang học năm nhất cao trung, mái tóc dài ngang cổ, làn da trắng trẻo cùng khuôn mặt mang vẻ có chút thờ ơ tạo cho cậu một nét quyến rũ riêng biệt. Itsuki nói rằng cậu là một đứa trẻ hoàn hảo – đứng đầu lớp học, giỏi thể thao, và cả những lĩnh vực khác nữa. Từ nhà Hashima đến căn hộ mà Itsuki đang sống cần tốn khoảng 20 phút lắc lư trên xe buýt, Chihiro thường xuyên ghé qua để phụ giúp việc nấu ăn, dọn dẹp phòng ốc… Điều này đã giúp cậu quen biết Toki.</p>" +
                            "<p id=\"85\">“Em lại đến nấu bữa tối hả?”</p>" +
                            "<p id=\"86\">“Vâng ạ.”</p>" +
                            "<p id=\"87\">“…Hầy, cậu ta thật may mắn khi có em là em trai. Anh thật sự ghen tỵ đấy.”</p>" +
                            "<p id=\"88\">“A, không, không hẳn…” Chihiro thoáng xấu hổ bởi những lời thật lòng của Toki. “Bây giờ có lẽ em phải đi rồi. Anh có yêu cầu gì với anh hai thì có thể nói với em nhé.”</p>" +
                            "<p id=\"89\">Sau khi cúi chào lịch sự, Chihiro rời đi. Toki vừa nhìn theo bóng lưng cậu vừa thầm nghĩ cậu thật sự là một đứa trẻ tốt bụng và lịch sự. Cậu nấu ăn ngon, luôn quan tâm chăm sóc cho anh trai của mình. Thật hoàn hảo.</p>" +
                            "<p id=\"90\">“…Ước gì Chihiro là con gái nhỉ. Nếu vậy thì Itsuki đã không phát điên vì cái sở thích cuồng em gái đến thế… À, vậy thì cậu ta sẽ không trở thành một tiểu thuyết gia như bây giờ mất… Cuộc sống mà…”</p>" +
                            "<p id=\"91\"><img src=\"https://i.docln.net/lightnovel/illusts/u114052-e0c5f098-317b-41d7-b260-3aa6454479c2.jpg\" alt=\"u114052-e0c5f098-317b-41d7-b260-3aa6454479c2.jpg\"></p>" +
                            "<p id=\"92\">***</p>" +
                            "<p id=\"93\">Vài phút sau khi Toki rời đi, Itsuki lần nữa nghe thấy tiếng chuông. Cậu mẫm chắc đó là Chihiro và tiến ra cừa.</p>" +
                            "<p id=\"94\">“…Chào em.”</p>" +
                            "<p id=\"95\">“Chào.”</p>" +
                            "<p id=\"96\">“…Mm.”</p>" +
                            "<p id=\"97\">Sau vài câu chào lí nhí, Itsuki để Chihiro vào nhà. Mặc dù họ có thể là người nhà, nhưng vẫn có chút khác thường ở đây, sự khó xử trong cách họ tiếp xúc với nhau. Họ trở thành anh em kế của nhau vào ba năm trước, khi bố của Itsuki tái hôn với mẹ của Chihiro – ngay khi Itsuki vừa ra mắt dưới danh nghĩa tiểu thuyết gia chuyên nghiệp. Đứa con trai lớn đang học năm hai cao trung, đứa nhỏ thì học năm nhất sơ trung – một khoảng thời gian để thay đổi cảm xúc cho cả hai. Việc bất ngờ trở thành anh em khiến hai người trở nên lúng túng, không biết phải cư xử với nhau như thế nào cho phải phép, ngay thời gian đầu họ chỉ hành xử như những người bạn chung phòng đang cố gắng sống cùng nhau.</p>" +
                            "<p id=\"98\">Mọi chuyện chỉ thay đổi khi Itsuki học lên đại học và dọn ra ở riêng. Cậu có thể tiếp tục sống tại nhà cùng những người khác mà không gặp khó khăn trong việc di chuyển, nhưng cậu vẫn quyết định thuê căn hộ này, bằng tiền của chính mình và bảo rằng việc này giúp cậu tiết kiệm thời gian để tập trung vào công việc viết lách của bản thân. Sau đó cậu đã thôi học đại học ngay năm nhất và tiếp tục sống ở đây bởi nó khá gần nơi biên tập viên của cậu làm việc.</p>" +
                            "<p id=\"99\">Trong khoảng thời gian Itsuki bỏ học, Chihiro thi thoảng vẫn ghé qua để đưa gạo cùng các loại thực phẩm thiết yếu. Bây giờ thì nó đã trở thành thông lệ, em trai của Itsuki sẽ chăm lo việc nấu ăn cũng như các loại việc nhà khác. Việc trở thành tiểu thuyết gia toàn thời gian giúp tiến độ hoàn thành của Itsuki ngày một nhanh hơn, nhưng bù vào đó là kỹ năng sống vốn nghèo nàn của cậu cứ thế mà tệ đi. Đã có thời điểm mà việc ăn ngủ của cậu trở nên vô cùng tệ hại, căn hộ mà cậu sống thì trở thành một đống hỗ lốn chính hiệu. Kể từ đó, Chihiro không thể đứng yên nhìn cái cách sống bừa bãi của cậu được nữa.</p>" +
                            "<p id=\"100\">“Đợi tý, em sẽ nấu xong nhanh thôi.”</p>" +
                            "<p id=\"101\">“…Ừm.”</p>" +
                            "<p id=\"102\">Bây giờ, Chihiro đã mặc tạp dề lên người, xử lý nguyên liệu mà mình mang đến và tiến hành nấu ăn trong căn bếp đã quen thuộc. Itsuki thi thoảng lại liếc nhìn cậu trong khi vẫn thao tác trên laptop, tiếp tục tiểu thuyết của mình. Ba mươi phút sau, họ đã ngồi đối diện nhau trên bàn.</p>" +
                            "<p id=\"103\">“Cảm ơn vì bữa ăn.”</p>" +
                            "<p id=\"104\">“Không có gì.”</p>" +
                            "<p id=\"105\">Chihiro đã làm món tôm với sốt cay, vài món xào kiểu Trung và cả cơm chiên – tất cả đều là tự làm và không món nào Itsuki có thể làm được cả, chúng tỏa ra một hương vị ngon lành, khiến đôi đũa của Itsuki không ngừng bận rộn. Chihiro nở một nụ cười nhẹ khi trông cách mà anh trai cậu thưởng thức các món ăn, không quên nhắc nhở anh mình việc ăn uống sao cho thích hợp. Dù đang lúc ăn cơm đi nữa, cậu vẫn thể hiện được sự mẫu mực của mình. Tinh tế, lôi cuốn, thành tích đứng đầu lớp, thể chất tốt, nấu ăn ngon, biết làm việc nhà, tính cách dễ chịu, hành vi chuẩn mực – thật sự là một người em trai hoàn hảo. Là một người anh trai – hay thậm chí là một thằng đàn ông – Itsuki không thể không cảm thấy có chút cảm giác tự ti. Nó làm cậu muốn tìm hiểu rõ hơn về Chihiro.</p>" +
                            "<p id=\"106\">“…Này, ưm, Chihiro? Anh thật sự quý việc em thường xuyên đến đây, nhưng mà, ừm, em không có gì quan trọng hơn để làm sao? Kiểu như… đi hẹn hò với bạn gái của em chẳng hạn?”</p>" +
                            "<p id=\"107\">Chihiro có chút bất mãn, “Em không có bạn gái.”</p>" +
                            "<p id=\"108\">“Không có?”</p>" +
                            "<p id=\"109\">“Không.”</p>" +
                            "<p id=\"110\">Đây chắc chắn là vì cậu chưa tìm được người thích hợp với bản thân. Không đời nào Chihiro, trong số tất cả mọi người, lại không thu hút được sự chú ý của người khác giới.</p>" +
                            "<p id=\"111\">“Sao em không thử tìm một người đi?”</p>" +
                            "<p id=\"112\">“… Em không biết nữa. Em nghĩ là em không thật sự muốn,” cậu nói tiếp. “Hơn nữa, anh biết đấy… Em khá quan ngại về anh đấy.”</p>" +
                            "<p id=\"113\">“Aw, hầy, Chihiro, em không cần phải lo lắng cho anh đâu!”</p>" +
                            "<p id=\"114\">Chihiro khẽ thở dài.</p>" +
                            "<p id=\"115\">“… Được rồi, em sẽ làm vậy nếu anh chịu hợp tác một chút đó?”</p>" +
                            "<p id=\"116\">“Này, anh làm được đấy! Chỉ cần muốn là anh có thể đấy.”</p>" +
                            "<p id=\"117\">“Thật ư? Vậy anh có thể tự nấu ăn ngày ba bữa? Tất nhiên là những món ăn thật sự, có rau và món ăn kèm, không phải mấy loại mì ăn liền? Anh cũng sẽ cọ rửa nhà tắm cũng như xử lý rác đúng nơi chứ? Anh sẽ cất mấy cái đĩa game khiêu dâm của anh lên kệ chứ?”</p>" +
                            "<p id=\"118\">“T-tất nhiên…”</p>" +
                            "<p id=\"119\">“Không, anh không thể,” Chihiro trả lời ngay lập tức.</p>" +
                            "<p id=\"120\">“Nhìn này, anh hai, anh vừa giặt cái áo len của mình chung với những thứ đồ bình thường khác đúng không? Anh lúc nào cũng thế. Tống mọi thứ cần giặt vào máy, đổ bậy đổ bạ lượng bột giặt mà anh cho là đúng, thiết lập đại một chế độ giặt nào đó rồi nhấn nút.”</p>" +
                            "<p id=\"121\">Itsuki cau mày trước những lời chỉ trích bản thân không sai vào đâu được.</p>" +
                            "<p id=\"122\">“Có việc thiết lập máy giặt nữa à…? Um… Nh-nhưng có một việc em sai rồi, Chihiro!”</p>" +
                            "<p id=\"123\">“Em sai ư?”</p>" +
                            "<p id=\"124\">“Đúng vậy, anh không hề cho bột giặt bậy bạ vào đó! Chúng hết rồi và anh cũng không hề đi mua thêm!”</p>" +
                            "<p id=\"125\">“Ồooo, thành tích đáng để tự hào đấy. Anh không biết là còn một chai nữa ở phía dưới bồn rửa tay à?”</p>" +
                            "<p id=\"126\">“…Ồ, có à?”</p>" +
                            "<p id=\"127\">“Ugh…” Lại một tiếng thở dài.</p>" +
                            "<p id=\"128\">“Anh không nghĩ mình sẽ thật sự toang nếu không có em à?</p>" +
                            "<p id=\"129\">Chihiro bình phẩm, nom cực-kỳ-hài-lòng.</p>" +
                            "<p id=\"130\"><img src=\"https://i.docln.net/lightnovel/illusts/u114052-cf8b2c69-8264-4c49-8c90-c3c74f4db3df.jpg\" alt=\"u114052-cf8b2c69-8264-4c49-8c90-c3c74f4db3df.jpg\"></p>" +
                            "")
                    .build();
            Chapter arafooChap0 = Chapter.builder()
                    .volume(arafoo)
                    .title("Ông chú tử vong")
                    .numberTitle("Chương 0")
                    .number(0)
                    .content("<p id=\"1\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-9e644480-a006-4a6f-a20a-94b586e6e61c.jpg\" alt=\"u17587-9e644480-a006-4a6f-a20a-94b586e6e61c.jpg\"></p>" +
                            "<p id=\"2\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-6ae7ca8c-bf36-4c86-8a6c-33e0b9623e16.jpg\" alt=\"u17587-6ae7ca8c-bf36-4c86-8a6c-33e0b9623e16.jpg\"></p>" +
                            "<p id=\"3\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-c802b985-8254-4ed2-b0ac-5a119944bae3.jpg\" alt=\"u17587-c802b985-8254-4ed2-b0ac-5a119944bae3.jpg\"></p>" +
                            "<p id=\"4\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-7b15fb73-1c9e-4624-974c-dce4cee08506.jpg\" alt=\"u17587-7b15fb73-1c9e-4624-974c-dce4cee08506.jpg\"></p>" +
                            "<p id=\"5\">Game nhập vai VR-RPG Sword and Sorcery VII.</p>" +
                            "<p id=\"6\">Vẫn luôn là Game nhập vai Full-Drive hàng đầu kể từ được phát hành riêng cho hệ thống máy chơi Game đời mới nhất Dream Work.</p>" +
                            "<p id=\"7\">Hiện đã được update đến phiên bản thứ 7 kéo theo lượng người chơi trung thành cuồng nhiệt vẫn đang gia tăng không ngừng.</p>" +
                            "<p id=\"8\">Hệ thống đồng bộ hóa giác quan được xây dựng thông qua việc kết nối các khớp thần kinh với các cổng kết nối điện tử, quá vượt trội so với bất kỳ hệ thống chơi game nào của các công ty khác. Thế giới ảo được trải nghiệm bằng cả năm giác quan, thu hút sự chú ý của các game thủ tìm cảm giác mạnh và khiến họ nghiện nặng.</p>" +
                            "<p id=\"9\">Cho dù giá bán máy rất đắt, nhưng đa số người chơi theo đuổi cái cảm giác kích thích đầy chất sử thi vẫn đắm chìm trong thế giới rộng lớn ngập tràn những cuộc phiêu lưu mạo hiểm ấy.</p>" +
                            "<p id=\"10\">Satoshi Osako là một game thủ như vậy, dùng toàn bộ thời gian để tận hưởng thế giới ảo tuyệt vời đó bằng nhân vật Zeros Merlin của mình.</p>" +
                            "<p id=\"11\">Nhân vật trong Game của anh ta để tóc mái mọc bừa bãi che khuất cả mắt, râu ria lởm chởm luộm thuộm cùng một khuôn mặt không có gì đặc biệt, cực kỳ giống một ông chú trung niên đã không còn tương lai. Một ông chú trung niên mặc trang bị cao cấp nhất đã được thiết kế tỉ mỉ để có bề ngoài trông y như trang bị cơ bản.</p>" +
                            "<p id=\"12\">Đại đa số người chơi khác đều không bao giờ cho rằng ông chú trung niên tầm thường trong chiếc áo choàng xám bẩn thỉu đầy khả nghi ấy lại là một trong những game thủ top 5 của trò chơi.</p>" +
                            "<p id=\"13\">Một trong những \"Destroyer\" đứng trên đỉnh cao của thế giới.</p>" +
                            "<p id=\"14\">Chế tạo trang bị, vũ khí hay Items phụ trợ vốn là chuyện đương nhiên sẽ có trong mọi trò chơi, điểm hấp dẫn nhất trong Sword and Sorcery VII chính là khả năng sáng tạo ma thuật.</p>" +
                            "<p id=\"15\">Trong thế giới ảo này, phép thuật hoạt động bằng cách sắp xếp 56 ký tự cơ bản và 10 chữ số Arab theo thứ tự nhất định, sinh ra đủ loại hiệu quả ma thuật khác nhau.</p>" +
                            "<p id=\"16\">Hệ thống Spell Circuits - tên gọi chung cho mọi ma thuật có thể khắc ghi vào trong tiềm thức, cho phép người chơi tùy chỉnh sức mạnh và hiệu ứng của phép thuật một cách thủ công, dẫn đến tình huống tăng giảm lượng Mana tiêu thụ và cả uy lực của câu thần chú. Vấn đề kỳ quặc ở chỗ ma thuật càng tinh xảo, càng phức tạp, uy lực lại càng tăng trong khi lượng Mana tiêu hao lại càng giảm.</p>" +
                            "<p id=\"17\">Cho dù đã cố gắng tính toán và điều chỉnh sao cho sát thương gây ra sẽ bằng 0 theo lý thuyết, phép thuật được triển khai vẫn cứ liên tiếp phát huy ra uy lực kinh khủng, dẫn đến việc người chơi đâm đầu vào tìm hiểu các sửa đổi có sẵn cho mỗi phép thuật. Hệ thống quá phức tạp gây ra hỗn loạn, thậm chí khiến trò chơi bị dán nhãn “game rác” trong khoảng thời gian đầu.</p>" +
                            "<p id=\"18\">Sau vô số giờ liều mình cày cuốc, cuối cùng người chơi cũng phát hiện ra bí mật được che giấu, rằng nếu sử dụng Mana của nhân vật ảo làm môi giới để vận dụng Mana tự nhiên mới có thể khiến uy lực của phép thuật được triển khai tăng lên. Chỉ cần phù hợp điều kiện, ma pháp thuật thức có thể triển khai, không cần biết là thô thiển vụng về đến mức nào, ma thuật đều có thể hoàn thành. </p>" +
                            "<p id=\"19\">Tuy nhiên, vì lượng Mana trong tự nhiên không được thể hiện ra bằng bất cứ tham số nào để tham khảo, cho nên để biết chính xác lượng Mana cần tiêu hao làm môi giới mỗi khi triển khai phép thuật thì trước mắt vẫn đang trong giai đoạn nghiên cứu, hiện tại chỉ có một cách: đoán.</p>" +
                            "<p id=\"20\">Spell Circuits gây hỗn loạn khi mới ra mắt, bởi không có gợi ý hay hướng dẫn gì về việc phép thuật có thể bị sửa đổi, việc phát hiện ra hệ thống Spell Circuits hoàn toàn là chuyện của tình cờ.</p>" +
                            "<p id=\"21\">Chỉ khi người chơi để tâm lưu ý và kiểm tra thật kỹ mới có thể phát hiện các manh mối chỉ dẫn khi khám phá thế giới trong trò chơi hay các Dungeon, bỏ qua hay nắm bắt gợi ý, tất cả đều tùy vào người chơi.</p>" +
                            "<p id=\"22\">Mặc dù trên phương diện là một trò chơi, nó mang đến sự tự do to lớn đến kinh người, nhưng chỉ những game thủ hard core thật sự chìm đắm trong thế giới Game mới có thể tận dụng được hệ thống Spell Circuits, những người chơi thông thường chỉ sử dụng phép thuật mặc định đã được tiêu chuẩn hóa.</p>" +
                            "<p id=\"23\">Thí nghiệm để tạo ra phép thuật mới tốn quá nhiều thời gian, cực kỳ phiền toái, thậm chí bị coi là một điểm trừ, đại đa số người chơi bình thường sẽ đi theo hướng tự do hưởng thụ mạo hiểm, khám phá thế giới.</p>" +
                            "<p id=\"24\">Tuy nhiên, tùy thuộc vào khả năng của người chơi, có thể tạo ra cả những phép thuật không có cast time hoặc cooldown time, rất nhiều người chơi bất mãn vì sự chênh lệch kinh khủng đó.</p>" +
                            "<p id=\"25\">Tuy nhiên, là một game thủ gạo cội hết lòng tận tụy với game, Satoshi hoàn toàn không thèm quan tâm đến ý kiến của người khác.</p>" +
                            "<p id=\"26\">Toàn bộ thành viên trong Party đều cho rằng hưởng thụ trò chơi như thế nào là tự do cá nhân của mỗi người. Bọn họ không chế tác những ma thuật mà bản thân sáng tạo ra thành Magic Scroll để bán, vì vậy mà thường xuyên bị chỉ trích vì giấu giếm cho riêng mình những ma thuật cao cấp mạnh mẽ. Dù vậy, không thèm quan tâm tới bất kỳ lời nào trong vô số những chỉ trích đầy tính hãm hại đó, bằng lòng nhiệt tình vượt xa người thường, bọn họ vứt bỏ mọi thường thức lẫn ánh mắt từ người khác dễ như trở bàn tay, vẫn lao đầu vào nghiên cứu sáng tạo ra đủ loại ma thuật. </p>" +
                            "<p id=\"27\">Đã trải qua bảy năm kể từ khi trò chơi được phát hành, Party của Satoshi vẫn luôn luôn độc chiếm vị trí tối cao, có thể gọi là những con nghiện Game hạng siêu nặng.</p>" +
                            "<p id=\"28\">Ma thuật mà họ sáng tạo ra, phức tạp đến bất thường, những người chơi theo hướng đào sâu khám phá hệ thống khác cũng cảm thấy nan giải thực sự khi phải đối mặt với hệ thống ma thuật mà Party của Satoshi đã sáng tạo ra, cho dù mọi kiến thức cần thiết để sáng tạo ma thuật đều có thể dễ dàng có được từ bất cứ thành phố nào.</p>" +
                            "<p id=\"29\">“Đừng có ỷ lại vào nỗ lực của người khác!” Đó là câu trả lời cho mọi lời thắc mắc.</p>" +
                            "<p id=\"30\">Ông chú trung niên Satoshi Osako, ngày xưa đã từng nổi tiếng vì là kỹ thuật viên lập trình cho một tập đoàn công nghệ hàng đầu, hiện tại đã nghỉ việc và về sống cô đơn nơi nông thôn.</p>" +
                            "<p id=\"31\">Kẻ bị rơi rớt khỏi guồng quay xã hội, ngoại trừ làm việc ngoài đồng ruộng ra thì chỉ có đắm chìm trong game cả ngày, cũng có thể gọi là một dạng Hikikomori.</p>" +
                            "<p id=\"32\">Vai trò một Đại Hiền Giả nổi tiếng với địa vị và sức ảnh hưởng to lớn trong thế giới ảo khiến ông chú càng chìm đắm vào sâu hơn vào trong nó.</p>" +
                            "<p id=\"33\">Ông chú trung niên, cử nhân thất nghiệp đã hơn 40 tuổi, không gia đình, cũng chẳng còn người thân nào ngoài một bà chị, nơi duy nhất ông chú được yên ổn là chính mình chỉ có thế giới ảo này.</p>" +
                            "<p id=\"34\">Ngoại hình sẽ dễ nhìn nếu chịu chăm chút cho bản thân, thậm chí có thể được hoan nghênh chào đón, nhưng cái lối sinh hoạt bừa bãi luộm thuộm đó đã khiến ông chú bỏ lỡ tuổi kết hôn mất rồi.</p>" +
                            "<p id=\"35\">Với quá khứ đó, ông chú vận dụng một phần kỹ thuật lập trình vào việc sáng tạo ra những phép thuật hùng mạnh, những thành viên khác trong Party cũng nắm giữ những kỹ thuật tương tự, không nghi ngờ gì khi nói rằng họ đã nhờ vậy mà cùng nhau tạo ra hàng loạt ma thuật ngày càng khủng khiếp hơn nữa. Trong thế giới ảo này, có thể gọi thành viên trong Party của Satoshi là những nhà nghiên cứu ngạo mạn điên cuồng mà lại ngốc nghếch.</p>" +
                            "<p id=\"36\">Với tâm thái phải chơi sao cho thật vui vẻ, bọn họ không ngừng sáng chế ra ma thuật uy lực mạnh mà lại tiêu hao ít Mana, càn quét vô số Quest khó cao cấp. Hiện tại, ông chú cùng đồng đội đang chiến đấu với Boss cuối trong cốt truyện - tà thần Evil God.</p>" +
                            "<p id=\"37\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-960a4357-f7c8-4023-b10f-f2a171df51af.jpg\" alt=\"u17587-960a4357-f7c8-4023-b10f-f2a171df51af.jpg\"></p>" +
                            "<p id=\"38\">Không biết họ đã chiến đấu trong bao lâu, chỉ biết rằng họ đã đi xa, rất xa, đã sắp đến lúc hạ được Boss cuối này.</p>" +
                            "<p id=\"39\">Evil God bị ép phải chuyển hóa sang dạng thứ ba, vốn tràn ngập khí thế đầy tăm tối của sự tà ác, bây giờ đã trở nên thê thảm tàn tạ dưới bàn tay của năm người.</p>" +
                            "<p id=\"40\">Cả năm người trong party đều là Pháp Sư, lại trang bị bằng hàng loạt vũ khí ma thuật cải tạo, áp đảo Evil God một cách tàn bạo ngay từ đầu bằng hỏa lực mãnh liệt.</p>" +
                            "<p id=\"41\">“Dai quá đi mất, sắp chết chưa ấy nhỉ?”</p>" +
                            "<p id=\"42\">“Đây là Boss cuối cơ mà, không chết dễ thế đâu!”</p>" +
                            "<p id=\"43\">“A ha… nó chuẩn bị tấn công kìa! Có cần triển khai ma thuật phòng ngự không?” </p>" +
                            "<p id=\"44\">Ma thuật công kích hung tàn của Evil God ào tới như muốn xé nát cả mặt đất.</p>" +
                            "<p id=\"45\">Bọn họ triển khai nhiều tầng vách chắn ma thuật, chống đỡ đòn tấn công, tiếp theo chớp thời cơ sơ hở xuất hiện trong nháy mắt, vung vũ khí chém tới.</p>" +
                            "<p id=\"46\">Cánh tay tà thần bị chặt phăng, rơi xuống cùng với tiếng vang lớn.</p>" +
                            "<p id=\"47\">Rõ ràng tất cả đều là pháp sư mà lại làm được như vậy có một phần rất lớn nhờ vào trang bị và ma thuật mà họ cùng nhau sáng tạo ra. Không tiếc tất cả, đầu tư chế tạo ra Items mạnh nhất theo sở thích của mỗi người, thoải mái dùng quái vật làm mục tiêu mà không ngừng thí nghiệm.</p>" +
                            "<p id=\"48\">Bọn họ đã khiêu chiến Evil God vô số lần, vẫn luôn thảm bại, lần khiêu chiến này là vì rửa nhục.</p>" +
                            "<p id=\"49\">“Kết thúc thôi chứ nhỉ? Lát nữa tôi còn phải đi làm!”</p>" +
                            "<p id=\"50\">“Được! Làm thịt hắn nhanh nào!”</p>" +
                            "<p id=\"51\">“Tôi yểm trợ. Nhớ mà cảm tạ đấy!”</p>" +
                            "<p id=\"52\">“Mong là rớt đồ hiếm!”</p>" +
                            "<p id=\"53\">“Tấn công kết liễu bằng đội hình thật là ngầu chứ nhỉ? Đối thủ là Boss cuối cơ mà, không thể hiện cho hoành tráng thì mất mặt danh xưng top đầu lắm!”</p>" +
                            "<p id=\"54\">Nở nụ cười sảng khoái không một chút sợ hãi, ma thuật tấn công với uy lực áp đảo ập đến như thủy triều, gần như lập tức giết chết Evil God.</p>" +
                            "<p id=\"55\">Những người chơi hoang đường lấy đánh quái làm niềm vui, nện hàng loạt ma thuật siêu hung tàn đến quá đáng lên tà thần, Boss cuối vào giờ phút này trông lại có vẻ đáng thương.</p>" +
                            "<p id=\"56\">Bị nhấn chìm trong vô số những vụ nổ, tà thần vừa phát ra tiếng kêu bi thương thảm thiết trước khi chết, vừa chậm rãi từ trên không trung rơi xuống như muốn đập tan mặt đất.</p>" +
                            "<p id=\"57\">“Kết thúc… Không hổ danh Boss cuối, quá khó đối phó!”</p>" +
                            "<p id=\"58\">“Giờ sao? Tôi không tham gia tiệc mừng đâu nhé, phải đi ngủ bây giờ...”</p>" +
                            "<p id=\"59\">“Lát nữa tôi có việc nên cũng không tham gia. Tắt máy đây…”</p>" +
                            "<p id=\"60\">“Tôi cũng vậy. Xin lỗi nhé, hẹn hôm khác bù...”</p>" +
                            "<p id=\"61\">“Vậy hôm nay giải tán. Tôi đi làm đây, mọi người ngủ ngon!”</p>" +
                            "<p id=\"62\">“ “ “ “ “Ngủ ngon!” ” ” ” ”</p>" +
                            "<p id=\"63\">Từng người dịch chuyển ra ngoài và đã đăng xuất hết, chỉ còn có Satoshi ở lại lâu đài của Evil God, kiểm tra những chiến lợi phẩm mà họ đã nhận được.</p>" +
                            "<p id=\"64\">Nhưng ông chú ở lại nơi này, trở thành khởi đầu của mọi chuyện.</p>" +
                            "<p id=\"65\">Ông chú không hề phát hiện ra Evil God hơi hơi giật giật, còn đang bận xem chỉ số của bản thân, quan sát điểm kinh nghiệm tích lũy, suy nghĩ xem tiếp theo sẽ học Skill nào. Lúc này cơ thể Evil God đột nhiên cử động.</p>" +
                            "<p id=\"66\">Trợn trừng mắt căm thù nhìn kẻ địch phía trước, chướng khí phun trào từ trong cơ thể.</p>" +
                            "<p id=\"67\">“Ta không cho phép… không tha thứ… lũ sinh vật thấp kém… hủy diệt ta…”</p>" +
                            "<p id=\"68\">“Cái quái…! HP bằng 0 rồi cơ mà…”</p>" +
                            "<p id=\"69\">“Ta nguyền rủa! Lũ nữ thần kinh tởm phong ấn ta… Lũ ngu xuẩn vô tri chống lại ta… Ta sẽ mang tất cả các người theo cùng!”</p>" +
                            "<p id=\"70\">“Event còn chưa kết thúc à? Không thể nào…?!”</p>" +
                            "<p id=\"71\">Giải phóng tất cả căm hận, Evil God tung ra lời nguyền.</p>" +
                            "<p id=\"72\">Ánh sáng đỏ đậm nhấn chìm tất cả.</p>" +
                            "<p id=\"73\">Ngày hôm đó, hệ thống điện trên toàn Nhật Bản ngừng hoạt động.</p>" +
                            "<p id=\"74\">Vài ngày sau, chính phủ phát hiện ra mười mấy người đã tử vong với nguyên nhân cái chết là “bí ẩn”.</p>" +
                            "<p id=\"75\">Việc cấp bách lúc này là chữa trị hệ thống điện, những cái chết bị quên đi giữa vô vàn xôn xao hỗn loạn.</p>" +
                            "<p id=\"76\">Xuất hiện lần cuối cùng ở một góc nhỏ trên báo, dần bị lãng quên theo thời gian.</p>" +
                            "")
                    .build();
            Chapter arafooChap1 = Chapter.builder()
                    .numberTitle("Chương 1")
                    .volume(arafoo)
                    .number(1)
                    .title("Ông chú chuyển sinh đến dị giới")
                    .content("<p id=\"1\">Khi tỉnh lại, ông chú đã thấy mình đang đứng trong một khu rừng tươi tốt.</p>" +
                            "<p id=\"2\">Tại sao ông chú lại ở đây, làm sao ông chú đến được đây… không có manh mối gì cả.</p>" +
                            "<p id=\"3\">Bốn phía đều là rừng rậm, cây cối tầng tầng vây quanh, một số loài cây còn chưa bao giờ nhìn thấy.</p>" +
                            "<p id=\"4\">“Mình đang ở đâu? Chắc chắn là mình đang ngồi chơi game trong phòng…”</p>" +
                            "<p id=\"5\">Gagyat, Gaguaya!</p>" +
                            "<p id=\"6\">“...”</p>" +
                            "<p id=\"7\">Những chú chim mang sắc màu ảo mộng bay qua đầu khiến ông chú Satoshi nghẹn lời.</p>" +
                            "<p id=\"8\">Chúng chắc chắn không phải chim ở Trái Đất, càng khiến ông chú cảm thấy hoang mang.</p>" +
                            "<p id=\"9\">Phải nói rằng nơi này không phải Trái Đất, rừng rậm hùng vĩ trải dài theo mọi hướng, trên trời còn có hai mặt trăng.</p>" +
                            "<p id=\"10\">“Chỗ này trông không giống Nhật Bản tý nào. Chuyện gì đã xảy ra? Thực vật kì quái mọc khắp nơi, cả trong tranh ảnh mình cũng chưa từng thấy chúng bao giờ…”</p>" +
                            "<p id=\"11\">Một cái cây to lớn trông như sự kết hợp giữa cây nắp ấm và hoa vua Rafflesia Arnoldii, đang dùng dây leo kéo một con gì đó trông như sói vào trong đóa hoa, tiếng xương cốt bị cắn nát vang lên không lâu sau đó... </p>" +
                            "<p id=\"12\">Trái Đất thân yêu chắc chắn không tồn tại thứ cây nguy hiểm như thế này, tuyệt đối không thể có loại cây ăn thịt cao hơn 2 mét thế này, càng không thể có cái loại hoa mọc đầy răng nhọn bên trong như thế này.</p>" +
                            "<p id=\"13\">Đột ngột,  ông chú  cảm thấy có gì đó khác lạ bên hông, cảm giác khác lạ đó kéo ánh mắt ông chú  nhìn xuống để rồi thứ lọt vào trong mắt là thứ mà lý trí đang cố gắng cự tuyệt. </p>" +
                            "<p id=\"14\">Không có nhiều hoa văn trang trí, mộc mạc, rõ ràng là vũ khí tạo ra thuần túy vì chiến đấu, vật phẩm đã quá quen thuộc trong game phản chiếu vào trong mắt. Rõ ràng không nghi ngờ gì nữa, chúng là kiếm.</p>" +
                            "<p id=\"15\">Hai thanh đoản kiếm, dùng một tay có thể múa lên được, sắc bén, đầy uy lực, chỉ có thể rèn bởi người chơi bằng vô số nguyên liệu quý hiếm, đang treo bên hông.</p>" +
                            "<p id=\"16\">Đây là thế giới game!? Không thể nào, lý trí hoàn toàn tỉnh táo của một người bình thường ra sức phủ nhận điều đó.</p>" +
                            "<p id=\"17\">Vớ vẩn, quá vớ vẩn…</p>" +
                            "<p id=\"18\">Phủ trên người ông chú  là một cái áo choàng xám hơi bân bẩn, đúng, chính là cái áo mà Zeros Merlin vẫn mặc. Cho dù không muốn, hiện thực vẫn cứ đập vào mắt.</p>" +
                            "<p id=\"19\">Cái áo choàng trông bẩn bẩn ấy là items có sức phòng ngự cực cao làm bằng da lột xuống từ trên người Behemoth, cùng chất liệu với áo giáp da thuộc đang mặc.</p>" +
                            "<p id=\"20\">“Ha, ha ha… làm sao có thể? Chuyển sinh đến thế giới game à? Đấy không phải là loại light novel nhan nhản trên mạng…”</p>" +
                            "<p id=\"21\">Cười thôi, chỉ có thể cười, có cố phủ nhận đến mức nào thì đáp án vẫn sờ sờ trước mặt.</p>" +
                            "<p id=\"22\">“Status… open… Đùa...”</p>" +
                            "<p id=\"23\">Ông chú chỉ định tự đùa một chút mà buột miệng thốt ra, nhưng cái bảng tham số quen thuộc trong game hiện lên khiến não ông chú Satoshi gần như ngừng hoạt động.</p>" +
                            "<p id=\"24\">“Đây… không phải là trò đùa!? Đùa thế này hơi lố rồi… Chuyện quái gì đã xảy ra với mình?!”</p>" +
                            "<p id=\"25\">==========</p>" +
                            "<p id=\"26\">Zeros Merlin</p>" +
                            "<p id=\"27\">Level 1879</p>" +
                            "<p id=\"28\">HP: 87,964,503 / 87,964,503</p>" +
                            "<p id=\"29\">MP: 17,932,458 / 17,932,458</p>" +
                            "<p id=\"30\">Job: Đại Hiền Giả</p>" +
                            "<p id=\"31\">Class-Skills:</p>" +
                            "<p id=\"32\">Thần Ma Thuật : God  - Max</p>" +
                            "<p id=\"33\">Thần Giả Kim : God  - Max</p>" +
                            "<p id=\"34\">Thần Rèn Đúc : God  - Max</p>" +
                            "<p id=\"35\">Thần Y Dược : God  - Max</p>" +
                            "<p id=\"36\">Thần Trang Bị Ma Thuật : God  - Max</p>" +
                            "<p id=\"37\">Kiếm Thần: God  - Max</p>" +
                            "<p id=\"38\">Giáo Thần: God  - Max</p>" +
                            "<p id=\"39\">Quyền Thần: God  - Max</p>" +
                            "<p id=\"40\">Thú Thần: God  - Max</p>" +
                            "<p id=\"41\">Ám Sát Thần: God  - Max</p>" +
                            "<p id=\"42\">Nấu ăn: (85/100)</p>" +
                            "<p id=\"43\">Trồng trọt: (56/100)</p>" +
                            "<p id=\"44\">Chăn nuôi: (24/100)</p>" +
                            "<p id=\"45\">Physical Skills:</p>" +
                            "<p id=\"46\">Kháng toàn bộ hiệu ứng dị thường - Max</p>" +
                            "<p id=\"47\">Tương thích với tất cả thuộc tính ma thuật  - Max</p>" +
                            "<p id=\"48\">Kháng nguyên tố  - Max</p>" +
                            "<p id=\"49\">Cường hóa thân thể vật lý - Max</p>" +
                            "<p id=\"50\">Cường hóa phòng ngự  - Max</p>" +
                            "<p id=\"51\">Cường hóa ma lực  - Max</p>" +
                            "<p id=\"52\">Thao túng ma lực  - Max</p>" +
                            "<p id=\"53\">Ma thuật ông chú thượng - Max</p>" +
                            "<p id=\"54\">Võ thuật ông chú thượng  - Max</p>" +
                            "<p id=\"55\">Chế tạo ông chú thượng   - Max</p>" +
                            "<p id=\"56\">Mắt thẩm định   - Max</p>" +
                            "<p id=\"57\">Mắt âm dương (nhìn thấy dạng linh hồn)  - Max</p>" +
                            "<p id=\"58\">Trực giác  - Max</p>" +
                            "<p id=\"59\">Tầm nhìn đêm   - Max</p>" +
                            "<p id=\"60\">Ẩn nấp  - Max</p>" +
                            "<p id=\"61\">Lục soát kẻ địch   - Max</p>" +
                            "<p id=\"62\">Cảnh giới - Max</p>" +
                            "<p id=\"63\">Thăm dò khoáng vật   - Max</p>" +
                            "<p id=\"64\">Thăm dò thực vật  - Max</p>" +
                            "<p id=\"65\">Phát hiện khí tức  - Max</p>" +
                            "<p id=\"66\">Che giấu khí tức  - Max</p>" +
                            "<p id=\"67\">Cảm nhận mana  - Max</p>" +
                            "<p id=\"68\">Chế tạo  - Max</p>" +
                            "<p id=\"69\">Phân giải  - Max</p>" +
                            "<p id=\"70\">Cường hóa  - Max</p>" +
                            "<p id=\"71\">Dịch tự động  (1/100)</p>" +
                            "<p id=\"72\">Hiểu tự động (1/100)</p>" +
                            "<p id=\"73\">Tự động chuyển hóa chữ viết : (1/100)</p>" +
                            "<p id=\"74\">Bách khoa toàn thư về quái vật  - Max</p>" +
                            "<p id=\"75\">Bách khoa toàn thư về nguyên liệu  - Max</p>" +
                            "<p id=\"76\">Phá vỡ nguyên hạn - Max</p>" +
                            "<p id=\"77\">Phá vỡ giới hạn - Max</p>" +
                            "<p id=\"78\">Phá vỡ cực hạn  - Max</p>" +
                            "<p id=\"79\">Interpersonal Skills:</p>" +
                            "<p id=\"80\">Sách phép của Merlin  - Max</p>" +
                            "<p id=\"81\">Công thức chế tạo   - Max</p>" +
                            "<p id=\"82\">Không gian nhà kho - Max</p>" +
                            "<p id=\"83\">==========</p>" +
                            "<p id=\"84\">“Này… vượt quá xa khỏi cái gọi là người thường rồi! Cái gì trông cũng nguy hiểm... siêu nhân à?! Thật hay giả…”</p>" +
                            "<p id=\"85\">Năng lực này rõ ràng không thuộc về con người.</p>" +
                            "<p id=\"86\">Mặc cho tiêu chuẩn căn bản của thế giới này như thế nào, nó vẫn cứ phi thường, phi logic…</p>" +
                            "<p id=\"87\">Trên thực tế, ông chú ở trong game đã là vô song, ma thuật chế tạo ra cũng ở cấp bậc tương đương với Evil God. Tuy lúc đó là năm đánh một, nhưng bọn họ đã thắng, những kẻ vượt qua thánh thần chắc chắn không nằm trong phạm vi người thường nữa rồi.</p>" +
                            "<p id=\"88\">Mặt xám như tro tàn, ông chú vừa nhìn vừa gõ gõ vào bảng trạng thái một cách trống rỗng.</p>" +
                            "<p id=\"89\">“Hửm… cái này là… tin nhắn à?! Người gửi… không biết… thật khả nghi...”</p>" +
                            "<p id=\"90\">Phát hiện góc dưới bảng trạng thái có dòng chữ đỏ nhấp nháy biểu hiện mới nhận được thư, ngón tay run rẩy của ông chú bấm vào nút “mở”</p>" +
                            "<p id=\"91\">“Xem nào… Hả?! Nữ thần?!!!!!”</p>" +
                            "<p id=\"92\">Tựa đề: “Gửi từ nữ thần: Về tình trạng hiện tại của bạn ♡”</p>" +
                            "<p id=\"93\">Vừa thấy cái ký hiệu trái tim đó ở tựa đề, ông chú đã cảm thấy có mùi bất ổn, khiến niềm tin của ông chú vào nội dung tin nhắn tụt dốc không phanh, thậm chí hoài nghi hiện thực, phân vân không biết liệu có phải mình vướng vào trò đùa dai nào đó. </p>" +
                            "<p id=\"94\">“Yahooo ~ ♡ Nữ thần Freya -chan đây! Đầu ngẩng cao đầu quá đấy, cúi thấp xuống một chút đi ♡”</p>" +
                            "<p id=\"95\">Ngay dòng đầu tiền, ông chú đã cảm thấy hối hận.</p>" +
                            "<p id=\"96\">“Xóa nó đi ngay thôi nhỉ?! Cái mùi âm mưu hôi hám đang bốc lên lộ liễu… chắc chắn có kẻ đứng sau giở trò...”</p>" +
                            "<p id=\"97\">Ông chú cảm thấy mơ hồ… không, cảm thấy chắc chắn rằng đây là phiền phức.</p>" +
                            "<p id=\"98\">Trong tình huống đang căng thẳng lại đọc phải câu từ kiêu căng ngạo mạn như đang công kích, thật sự khiến người ta khó chịu.</p>" +
                            "<p id=\"99\">“Không có nhiều thời gian nên sẽ nói ngắn gọn ha. Tầm 2487 năm trước, các anh hùng phải phong ấn tà thần, địa điểm phong ấn chính là thế giới trong Game của các ngươi đấy. </p>" +
                            "<p id=\"100\">Hy sinh rất nhiều mới phong ấn được nó, gần đây thì nó bắt đầu sống lại, chúng ta đành phải phong ấn nó lần nữa sang thế giới khác…. Gọi là Evil God War... Ah ha ha ha ~ ♡”</p>" +
                            "<p id=\"101\">Quả nhiên nội dung không ổn chút nào. Ông chú Satoshi nảy ra vài ý tưởng nhưng vẫn cố nén giận, bình tĩnh, đọc tiếp.</p>" +
                            "<p id=\"102\">“Không được tùy tiện vứt lung tung rác thải nguy hiểm không đốt được sang thế giới loài người? Nhưng thật sự hết cách rồi, sau đó… ta nghĩ nếu là thế giới trong trò chơi thì ngay cả các ngươi cũng hạ được nó ấy chứ. Kết quả là các ngươi cũng đã hạ được nó đấy thôi, quá là xuất sắc. Cái thứ phiền phức đó, xấu xí như vậy mà cũng là một nữ thần đấy!\"</p>" +
                            "<p id=\"103\">“Đó mà là nữ thần!? Trông như một đống nội tạng chắp vá. Thật hay giả...”</p>" +
                            "<p id=\"104\">Nhớ lại lúc đối đầu với Evil God, một đống nội tạng chồng chất, dung hợp đủ thứ bộ phận của đủ loại sinh vật, tùy tiện chắp vá ghép nối lộn xộn khủng khiếp, khiến người ta dựng tóc gáy. </p>" +
                            "<p id=\"105\">Nhớ lại thôi đã thấy tởm lợm. Tuyệt đối không có một mẩu nào liên quan dính dáng gì đến nữ thần hết. </p>" +
                            "<p id=\"106\">“Mà cũng không ngờ nó lại chơi trò tự hủy áh! Chỉ để lôi các ngươi chết chung, làm người ta hết hồn ~ ♡! </p>" +
                            "<p id=\"107\">Ta cùng ba nữ thần nữa quyết định đem hết mười mấy người bị chết ở đó về đây, dùng dữ liệu trong game tái tạo lại tất cả các ngươi trong thế giới này ~ ♡”</p>" +
                            "<p id=\"108\">“Mười mấy người? Gồm cả tôi?! Bị giết chết?! Rốt cuộc có bao nhiêu người trở thành vật hy sinh?! Khốn nạn…”</p>" +
                            "<p id=\"109\">Bọn họ bị hại chết, là nạn nhân của một vụ ô nhiễm rác thải công nghiệp không thể tiêu hủy.</p>" +
                            "<p id=\"110\">“Bởi vì các ngươi đã giết ác thần, phần thưởng đặc biệt là được tái sinh với dữ liệu y hệt trong game. Thế giới trong game và thế giới này gần như không khác biệt, cho nên chuyển sinh rất dễ. Mặc dù các ngươi không tự nguyện, nhưng ta đã làm quá tốt ~ ♡”</p>" +
                            "<p id=\"111\">“Thật muốn đập cô ta. Ép người khác giải quyết vấn đề nguy hiểm mình gây ra, không hề hối lỗi, không hề xin lỗi. Mình sẽ đánh cả lũ đó cho đến phát khóc…”</p>" +
                            "<p id=\"112\">Trong khi hưởng thụ thú vui chơi game, cả cuộc đời lại bị cướp đi.</p>" +
                            "<p id=\"113\">Những người bị trở thành vật hy sinh, họ cũng có ước mơ, có tương lai, có cuộc sống riêng. Vì một lý do tùy tiện như vậy mà đột ngột bị cuốn vào nguy hiểm, bị hại chết, thật sự không ai có thể chấp nhận được!</p>" +
                            "<p id=\"114\">“Tất cả items đã tái tạo hoàn tất. Cố lên nha ~ ♡.</p>" +
                            "<p id=\"115\">Nhưng items tiêu hao phải tự chế nha, công thức đã lưu trong não ngươi rồi, từ từ mà xem xét há ~ ♡</p>" +
                            "<p id=\"116\">Tuổi của ngươi bằng tuổi ở thế giới cũ, muốn trẻ lại thì tự chế thuốc đi ha</p>" +
                            "<p id=\"117\">Xin lỗi nha, xin lỗi…</p>" +
                            "<p id=\"118\">Waaaa… cái đám thần quản lý thế giới của ngươi đang phát khùng lên, hết cách rồi người ta mới phải làm vậy mà ~ ♡. Phục sinh người chết là làm trái quy tắc tự nhiên, rắc rối lắm, rất vất vả luôn… đám thần ở thế giới của ngươi đó, bọn họ sẽ rất vất vả cho coi ~ ♡</p>" +
                            "<p id=\"119\">Chính là như vậy, nên hưởng thụ quãng đời còn lại ở đây vui vẻ há.</p>" +
                            "<p id=\"120\">Gặp lại sau!</p>" +
                            "<p id=\"121\">Bye bye ~ ♡”</p>" +
                            "<p id=\"122\">“Hết cách cái gì! Con nữ thần này ích kỷ ông chú mức nào vậy?! Mà bọn chúng còn không thèm giải quyết hậu quả một chút nào?! Cướp đi cuộc đời người ta rồi còn sung sướng?! Đùa cái quái gì vậy!?”</p>" +
                            "<p id=\"123\">Giờ thì đã biết lý do, mà cũng chẳng giải quyết được gì cả, hiện tại ông chú vẫn không biết mình đang ở đâu, chỉ biết đứng trong khu rừng khổng lồ này.</p>" +
                            "<p id=\"124\">Quan trọng là, ông chú không chỉ nổi giận vì thái độ của nữ thần… mà nổi cả sát ý lên rồi.</p>" +
                            "<p id=\"125\">“Tạm thời là nắm rõ tình hình, vấn đề bây giờ là “có người sống ở gần đây hay không?”. Chỗ này trông như rừng rậm nguyên sinh.”</p>" +
                            "<p id=\"126\">Nguy hiểm nhất là lang thang mà không biết mình đang đi đâu. Nếu thế giới này giống thế giới trong game, tức là quái vật lang thang khắp thế giới.</p>" +
                            "<p id=\"127\">Sau khi suy xét, ông chú quyết định bắt đầu tìm kiếm từ trên cao.</p>" +
                            "<p id=\"128\">“Dùng được thì tốt… “Wings of the Dark Crow”!”</p>" +
                            "<p id=\"129\">“Wings of the Dark Crow” là ma thuật phi hành ông chú Satoshi sáng tạo ra trong game.</p>" +
                            "<p id=\"130\">Ma thuật “Fly” cơ bản hiệu suất quá thấp, tiêu hao quá cao, sử dụng một thuật thức khổng lồ để áp chế mana tiêu hao đến mức thấp nhất, ông chú thành công tạo ra một kiệt tác. Theo giả thiết cơ bản trong game, mọi cư dân trong thế giới này đều có thể sử dụng Mana xây dựng mô hình pháp thuật gọi là thuật thức, cấu trúc ma pháp thuật thức và lưu trữ trong não.</p>" +
                            "<p id=\"131\">Thông qua thuật thức đã lưu trong não để phát động ma thuật, đồng thời có thể sửa chữa cải tiến ma thuật đã lưu, đây chính là cách học tập và sáng tạo ma thuật.</p>" +
                            "<p id=\"132\">Chỉ cần thực hiện chính xác, ông chú cho rằng bản thân mình có thể sử dụng ma thuật trong thế giới này.</p>" +
                            "<p id=\"133\">Ma pháp trận hình sao tám cánh xuất hiện cả trên đầu, dưới chân cùng bốn phía xung quanh, cộng hưởng, hòa trộn, tạo thành thuật thức ma pháp phức tạp. Ma pháp trận bao trùm cơ thể, hình thành phản trọng lực trường, giải phóng ông chú khỏi xiềng xích của trọng lực, rời xa mặt đất, hướng về phía bầu trời.</p>" +
                            "<p id=\"134\">“Owahhhh? Tuyệt vời… Bay! Bay lên rồi!”</p>" +
                            "<p id=\"135\">Ông chú trung niên hò hét như một đứa trẻ, sung sướng khi ma thuật của mình hoạt động.</p>" +
                            "<p id=\"136\">Niềm vui chẳng bao lâu cho đến khi nhớ ra mục đích của mình, ông chú bắt đầu tìm kiếm, từ trên cao nhìn xuống.</p>" +
                            "<p id=\"137\">“Chỉ có rừng, tìm đâu ra người bây giờ ! Ảo giác sao, mình cảm thấy có ác ý …”</p>" +
                            "<p id=\"138\"> Rừng rậm ngút ngàn trải xa tít tắp, núi non hùng vĩ, tuyệt đối không thấy bóng dáng con người. </p>" +
                            "<p id=\"139\">“Đây là hình phạt sao?! Đây…”</p>" +
                            "<p id=\"140\">Càu nhàu và chọn bừa một phương hướng để bay đi.</p>" +
                            "<p id=\"141\">Ông chú lang thang trong thế giới mới, như một chú chim di cư lạc bầy...</p>" +
                            "<p id=\"142\">==========</p>" +
                            "<p id=\"143\">Ông chú lặng lẽ hạ xuống mặt đất khi ma thuật gần hết tác dụng, sau nhiều lần lặp lại sử dụng ma thuật và nhiều giờ bay không ngừng nghỉ, vẫn không thấy bóng dáng thị trấn hay thôn làng nào.</p>" +
                            "<p id=\"144\">Bởi vậy, bây giờ buộc phải suy tính đến việc đi tìm đồ ăn và… ngủ ngoài trời.</p>" +
                            "<p id=\"145\">Đương nhiên, người còn sống thì phải ăn, không ăn sẽ chết đói.</p>" +
                            "<p id=\"146\">Giấc ngủ càng quan trọng hơn, hiện tại ông chú là người gặp nạn.</p>" +
                            "<p id=\"147\">“Nói như vậy, nhưng…”</p>" +
                            "<p id=\"148\">Trong tin nhắn có nói đã tái tạo lại tất cả items, nhưng tìm khắp cả hòm đồ cũng không thấy thức ăn đâu cả. Tuy trong game ông chú rất thích đi mạo hiểm cắm trại cùng đồng đội, thu thập nguyên liệu nấu ăn. Bây giờ cuộc đời ông chú biến thành game sinh tồn nơi hoang dã.</p>" +
                            "<p id=\"149\">May mắn là vẫn có gia vị, nhưng hoàn toàn không có nguyên liệu nấu ăn.</p>" +
                            "<p id=\"150\">“Chỉ còn cách đi săn… Động vật ở thế giới này có ăn được không nhỉ?”</p>" +
                            "<p id=\"151\">Vừa nói vừa làm, ông chú lấy cung và bao tên ra khỏi hòm đồ, đeo lên người.</p>" +
                            "<p id=\"152\">Tuy đã quyết định săn thú nhỏ làm thức ăn, nhưng vấn đề lớn hơn lại xuất hiện.</p>" +
                            "<p id=\"153\">“Mình chưa từng tự đi săn bao giờ. Anh bạn hàng xóm Yamada-san có hay rủ mình đi, nhưng mình chưa đi một mình bao giờ, cũng chưa bao giờ tự làm thịt thú…”</p>" +
                            "<p id=\"154\">Nơi ông chú từng ở là một ngôi làng nhỏ hẻo lánh sâu trong núi, có thể nhìn thấy biển nội địa từ trên núi, cho nên hàng xóm qua lại rất thân thiện. Ông chú từng đi săn heo rừng phá hoại mùa màng, rồi làm thịt nó, nhưng đó là nhờ anh thợ săn ở bên cạnh chỉ dẫn tỉ mỉ.</p>" +
                            "<p id=\"155\">Đây là lần đầu tiên ông chú săn thú một mình, không kiếm được thức ăn trong khu rừng toàn dã thú với quái vật thế này chỉ có chết đói. </p>" +
                            "<p id=\"156\">Cẩn thận là hơn, ông chú Satoshi quyết định làm giống như trong Game, kích hoạt skill để xóa bỏ sự hiện diện và tìm kiếm con mồi.</p>" +
                            "<p id=\"157\">Skill kích hoạt rất thuận lợi khiến ông chú khá ngạc nhiên, nhưng vấn đề ông chú quan trọng trước mắt là lương thực cái đã.</p>" +
                            "<p id=\"158\">“Có…”</p>" +
                            "<p id=\"159\">Con thỏ chui ra từ dưới bụi rậm, cảnh giác gặm cỏ trên mặt đất. </p>" +
                            "<p id=\"160\">==========</p>" +
                            "<p id=\"161\">Thỏ rừng</p>" +
                            "<p id=\"162\">Level 300</p>" +
                            "<p id=\"163\">HP: 2321/2321  </p>" +
                            "<p id=\"164\">MP: 514/514</p>" +
                            "<p id=\"165\">==========</p>" +
                            "<p id=\"166\">Thỏ là sinh vật rất cảnh giác, chỉ một tiếng động nhỏ vang lên cũng sẽ bỏ trốn.</p>" +
                            "<p id=\"167\">Thỏ còn có thói quen ăn phân, rất là đáng ghét. Nhưng mà ông chú cũng chỉ cần thịt chứ không cần nội tạng.</p>" +
                            "<p id=\"168\">Lắp tên, kéo căng dây cung, nấp kỹ trên cây, nhắm và nín thở chờ đợi, trong một khoảnh khắc khi con thỏ quay đi, bắn.</p>" +
                            "<p id=\"169\">Âm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm ầm...!</p>" +
                            "<p id=\"170\">Tiếng nổ điếc tai vang lên, uy lực khủng khiếp không thể tưởng tượng nổi là phát ra từ một cây cung... con thỏ cùng với mặt đất bắn tung lên trời.</p>" +
                            "<p id=\"171\">“Uy lực quá lớn à? Hay tại mình dùng cung quá tệ?! Săn thỏ thật là khó…”</p>" +
                            "<p id=\"172\">Thật đau đớn, ngài thỏ biến thành một đám thịt nát vụn thê thảm, ông chú dùng vũ khí quá mạnh rồi.</p>" +
                            "<p id=\"173\">Đại Hiền Giả nhìn chằm chằm không chớp mắt vào cây cung trên tay.</p>" +
                            "<p id=\"174\">==========</p>" +
                            "<p id=\"175\">Ma cung cải tạo số 32</p>" +
                            "<p id=\"176\">Lực công kích + 100.000</p>" +
                            "<p id=\"177\">Tăng cường thể lực</p>" +
                            "<p id=\"178\">Tăng gấp đôi thiệt hại</p>" +
                            "<p id=\"179\">Tăng khả năng tấn công</p>" +
                            "<p id=\"180\">Tăng độ chính xác</p>" +
                            "<p id=\"181\">Hiệu ứng nhất kích tất sát: Mục tiêu nổ tung</p>" +
                            "<p id=\"182\">===========</p>" +
                            "<p id=\"183\">“Không cẩn thận giết chóc vô nghĩa mất rồi…”</p>" +
                            "<p id=\"184\">Cái thứ này không phải vũ khí để săn thú. Tuy lúc làm ra nó cùng đồng đội chỉ để cho vui, nửa đùa nửa thật, trông cũng ra dáng vũ khí. Không ngờ lại vô dụng thế này.</p>" +
                            "<p id=\"185\">Còn chưa kịp lo lắng về vụ làm thịt, con mồi đã nổ tung văng tứ tán, không thể dùng làm lương thực được. </p>" +
                            "<p id=\"186\">“Từ từ… bình tĩnh lại nào… mình nhớ mình có skill “Kiềm Chế”, sử dụng nó sẽ có biện pháp…”</p>" +
                            "<p id=\"187\">Con mồi chết ngay lập tức kích hoạt hiệu ứng khi “Nhất kích tất sát”, sau đó “Mục tiêu nổ tung” tan xương nát thịt. Vậy thì chỉ cần dùng “Kiềm Chế” đánh cho gần chết, sau đó giết thật nhẹ nhàng là được. </p>" +
                            "<p id=\"188\">Ông chú tiếp tục tìm mồi.</p>" +
                            "<p id=\"189\">“Lần này nhất định phải thành công…”</p>" +
                            "<p id=\"190\">Lần nữa tìm thấy thỏ, nhẹ nhàng cẩn thận dùng tên đánh nó gần chết, sau đó dùng dao nhỏ kết liễu.</p>" +
                            "<p id=\"191\">Rút máu và làm thịt cần tiến hành khi con mồi còn sống là tốt nhất. May mắn, lần này không có gì phát nổ. Cuối cùng ông chú cũng có thể thở phào nhẹ nhõm.</p>" +
                            "<p id=\"192\">Vấn đề tiếp theo, làm thịt nó ở đâu đây?</p>" +
                            "<p id=\"193\">“Nếu được thì mổ cạnh suối nước là tốt nhất.”</p>" +
                            "<p id=\"194\">Dọc đường đi tìm nguồn nước, ông chú lại săn được ba con thỏ, tuy rằng bụng đói kêu vang thành tiếng, nhưng giờ chưa phải lúc, mùi máu tanh rất có thể thu hút quái vật ăn thịt tìm đến tấn công.</p>" +
                            "<p id=\"195\">Soạt, soạt soạt, soạt soạt soạt…</p>" +
                            "<p id=\"196\">Là nhãi nhép cơ bản trong thế giới huyền ảo.</p>" +
                            "<p id=\"197\">Là vua của bọn lâu la nhãi nhép, nhìn thấy một, xung quanh chắc chắn còn hàng trăm.</p>" +
                            "<p id=\"198\">Quái vật kinh điển bắt đầu bằng chữ G.</p>" +
                            "<p id=\"199\">Goblin phát hiện ra Satoshi, bắt đầu thổi kèn bóp còi inh ỏi như trong phim ảnh. Cả khu rừng xôn xao, vô số Goblin tuôn ra từ sâu trong rừng, số lượng ngày càng gia tăng.</p>" +
                            "<p id=\"200\">“Goblin?! Đùa à a a a a a a a a a a a…” </p>" +
                            "<p id=\"201\">Ông chú vội vàng bỏ chạy, như bay.</p>" +
                            "<p id=\"202\">Săn thú thì bình thường, giết thỏ thì không sao, nhưng ông chú chưa từng nghĩ đến việc chiến đấu với quái vật hình người.</p>" +
                            "<p id=\"203\">Không phải là không thắng nổi, mà vì ông chú là người sống trong xã hội hiện đại, căm ghét việc giết người, cách đây chưa lâu vẫn còn sống trong xã hội văn minh, tự nhiên bị ném vào môi trường sinh tồn tàn khốc như thế này, còn chưa thể tỉnh ngộ.</p>" +
                            "<p id=\"204\">Mất một chút thời gian để nhận ra rằng mình thật ngây thơ.</p>" +
                            "<p id=\"205\">Ông chú bỏ chạy, quân đoàn Goblins toàn lực truy đuổi. Ông chú chạy rất nhanh, nhưng Goblins rất đông.</p>" +
                            "<p id=\"206\">Bị chặn đường, chuyển hướng, lại xuất hiện cả đoàn Goblins chặn đường, số lượng ngày càng tăng, phải đến hàng trăm.</p>" +
                            "<p id=\"207\">“Khu rừng chết tiệt này… Là thế quái nào vậy hả a a a a a a a ...”</p>" +
                            "<p id=\"208\">Goblins không ngừng gia tăng số lượng, lúc này ông chú còn không biết, khu rừng này chưa từng có ai dám đặt chân đến, người ta sợ hãi gọi nó là “Rừng đại ngàn Fafranch”,</p>" +
                            "<p id=\"209\">Một vương quốc của quái vật và ma thú, không thiếu những bầy đàn đông tới hàng ngàn con, Goblins chỉ là thứ nhãi nhép hạng bét cơ bản.</p>" +
                            "<p id=\"210\">Định dùng ma thuật phi hành để trốn, nhưng tên bắn tới ông chú từ bốn phương tám hướng, né còn không kịp, không thể trốn lên trời, chỉ có thể liều mạng bỏ chạy, cho đến khi trước mặt xuất hiện ánh sáng.</p>" +
                            "<p id=\"211\">Như thiêu thân lao vào lửa, bản năng dẫn dắt ông chú lao về hướng ánh sáng.</p>" +
                            "<p id=\"212\">Thôn làng đây rồi, không, quy mô lớn như thế này, phải gọi là thị trấn mới đúng.</p>" +
                            "<p id=\"213\">“Đến rồi ! Được cứu rồi… Woahhh…”</p>" +
                            "<p id=\"214\">Chỉ trong nháy mắt, ông chú biết là mình lầm to. Nơi đó có cả quân đoàn Goblins, chính xác, nơi ông chú vừa chạy đến, là làng Goblins.</p>" +
                            "<p id=\"215\">Chạy trốn đến hang ổ quân địch, ông chú không thể ngưng cười.</p>" +
                            "<p id=\"216\">“A ha… A ha ha ha ha… Oa ha ha ha ha ha ha ha ha ha ha!”</p>" +
                            "<p id=\"217\">Lý trí của ông chú đã bị ép ông chú giới hạn, đã xuất hiện triệu chứng nguy hiểm.</p>" +
                            "<p id=\"218\">Sột soạt, soạt soạt, soạt soạt soạt…</p>" +
                            "<p id=\"219\">Goblins là sinh vật ăn tạp, cái gì cũng ăn. Trong môi trường sinh tồn tàn khốc, đối với động vật hoang dã, nhân loại chính là nguồn thức ăn quý giá. Đối với đàn Goblins không ngừng săn bắn, từ trước đến nay mới nhìn thấy nguyên liệu nấu ăn tuyệt vời như Satoshi.</p>" +
                            "<p id=\"220\">Nhưng đám Goblins không phát hiện ra một điều.</p>" +
                            "<p id=\"221\">Satoshi đứng trước mắt bọn chúng, là loại tồn tại mà bọn chúng tuyệt đối không thể đụng vào.</p>" +
                            "<p id=\"222\">“Tất cả… bay đi cho ta a a a a a a a a a a a a a…”</p>" +
                            "<p id=\"223\">Đột nhiên, bốn phía nổi lên bão tố, cơn bão uy lực mãnh liệt do Mana tạo thành, khiến đám quái vật kinh hoàng.</p>" +
                            "<p id=\"224\">Đã quá trễ, Satoshi đang phát động ma thuật cấm kỵ.</p>" +
                            "<p id=\"225\">“Dark Judgement (Hắc Ám Phán Quyết)!” </p>" +
                            "<p id=\"226\">Khối cầu đen to lớn cấu tạo từ ma lực khổng lồ xuất hiện, liên tục sinh ra nhiều khối cầu nhỏ cùng màu, nuốt chửng lũ Goblins.</p>" +
                            "<p id=\"227\">Quả cầu đen phóng ra tia sét, lốc xoáy cuộn lên nuốt chửng cả đàn Goblins cùng với mặt đất, giết chóc đi kèm những vụ nổ liên miên, cuộc chiến hóa thành đơn phương hủy diệt cùng tàn sát.</p>" +
                            "<p id=\"228\">Đây là ma thuật hung tàn nhất được ông chú tạo ra sau nhiều lần chiến đấu với Evil God, thảnh quả khi nghiên cứu phân tích phương thức tấn công của nó một cách khoa học.</p>" +
                            "<p id=\"229\">Màn trình diễn hủy diệt xóa sổ làng Goblins chỉ trong thoáng chốc, nhưng dường như còn chưa đủ, uy lực lan tràn tạo nên một vùng đất hoang tàn trơ trụi ngay giữa rừng rậm bao la.</p>" +
                            "<p id=\"230\">Dark Judgement - ma thuật siêu trọng lực, đơn giản là chế tạo ra lỗ đen đã đạt đến trạng thái giới hạn, sau đó bắn loạn xạ khắp nơi. Đàn Goblins bị phân giải thành Quax lượng tử, đồng thời bị hấp thu, chuyển hóa thành nhiên liệu, tiếp tục gây nổ trên diện rộng.</p>" +
                            "<p id=\"231\">Kẻ địch càng đông, công kích càng mạnh, tuyệt đối không dừng lại trước khi kẻ địch bị tiêu diệt hoàn toàn.</p>" +
                            "<p id=\"232\">Ma thuật tồn tại như cơn ác mộng.</p>" +
                            "<p id=\"233\">Sau khi khôi phục lý trí, thứ ông chú nhìn thấy là quang cảnh như mưa thiên thạch vừa rơi xuống, mặt đất bị khắc lên vô số hố thiên thạch to có nhỏ có, nhầm đây là bề mặt mặt trăng cũng không lạ.</p>" +
                            "<p id=\"234\">“Sai… mình sai rồi, tạo nên sai lầm không thể cứu vãn nổi rồi. Phá hoại môi trường, tàn sát… hậu quả còn khó giải quyết hơn bom nguyên tử…”</p>" +
                            "<p id=\"235\">Tuy là vì sinh tồn, nhưng ma thuật để lại hậu quả vượt quá tưởng tượng.</p>" +
                            "<p id=\"236\">Trên mặt đất hoang tàn, thứ từng là đàn Goblins giờ chỉ còn là một đống Magic Stone vương vãi.</p>" +
                            "<p id=\"237\">Magic Stone  là Mana kết tinh thành dạng rắn, cứng hơn cả kim cương, cho dù sử dụng ma thuật hủy diệt, thân thể tan biến vẫn sẽ có Magic Stone còn lại.</p>" +
                            "<p id=\"238\">Đương nhiên có không ít Magic Stone nát vụn, nhưng số lượng ông chú thu thập được vẫn rất nhiều. Nhưng đây không phải là vấn đề.</p>" +
                            "<p id=\"239\">“Phân giải đến mức lượng tử mà vẫn có Magic Stone lưu lại à? Thôi được rồi, tìm nguồn nước trước đã.”</p>" +
                            "<p id=\"240\">Nơi này vẫn còn tồn tại quy tắc tự nhiên mà ông chú chưa biết, sức mạnh của ông chú bây giờ tương đương với vũ khí cấp chiến lược, tuy phi thường, lại quá xa vời cuộc sống bình lặng yên ổn hằng mong muốn. Ngài Đại Hiền Giả bỗng cảm giác nặng nề chán nản như một hồn ma.</p>" +
                            "<p id=\"241\">Sau khi nhặt hết Magic Stone, ông chú bắt đầu di chuyển, lê lết đôi chân mỏi mệt suốt ba giờ không nghỉ, cuối cùng cũng tìm thấy một con suối. Nước suối trong vắt, nhìn thấy rõ cả cá bơi dưới đáy nước.</p>" +
                            "<p id=\"242\">“Làm thịt như thế nào bây giờ…? Mình mới chỉ học sơ qua từ Yamada-san…”</p>" +
                            "<p id=\"243\">Không thể chịu nổi cơn đói thêm nữa, ông chú quyết định làm thịt con mồi ngay bờ suối.</p>" +
                            "<p id=\"244\">Đao giải phẫu chuyên dụng để mổ thịt cũng xuất hiện như trong game, may mà ông chú cũng có kinh nghiệm đi săn, cũng từng làm thịt thú, nhưng tự làm một mình thì đúng là lần đầu tiên. Chưa kể xung quanh là thế giới hoang dã, quái vật có thể tấn công bất cứ lúc nào.</p>" +
                            "<p id=\"245\">Càng chờ lâu, khả năng bị tấn công sẽ càng tăng.</p>" +
                            "<p id=\"246\">Đến lúc ông chú quyết tâm bắt đầu, cảnh tượng trước mặt lại khiến ông chú giật mình.</p>" +
                            "<p id=\"247\">“Khoan đã! Mình đã mổ xong hết từ lúc nào rồi!? Không hề có ấn tượng...”</p>" +
                            "<p id=\"248\">Con thỏ đã bị giải phẫu thành từng phần gọn gàng xinh đẹp, da lông không dính một giọt máu.</p>" +
                            "<p id=\"249\">Cái cảnh dị thường này khiến ông chú thực sự hoang mang.</p>" +
                            "<p id=\"250\">“Không còn cách nào khác, thử thêm một con nữa… Cái gì?!”</p>" +
                            "<p id=\"251\">Trong nháy mắt khi tay ông chú cầm con thỏ, giống như bản năng tự phản ứng, biến con thỏ thành từng miếng thịt ngon lành đẹp mắt, tốc độ nhanh cùng chính xác đến kinh khủng.</p>" +
                            "<p id=\"252\">Chính mắt nhìn cũng phải thấy kinh ngạc.</p>" +
                            "<p id=\"253\">“Có lẽ… Là do class-skill?”</p>" +
                            "<p id=\"254\">Trong số Class-Skill của ông chú, có “Thú Thần” và “Phân Giải” tăng cường rất mạnh cho việc săn bắn này. </p>" +
                            "<p id=\"255\">Trong game, class-skill có 5 giai đoạn phát triển, chia làm “Tập sự”, \"Master\", \"Demon\", \"Emperor\", \"God\", ví dụ muốn trở thành kiếm sĩ, bắt buộc phải nghiên cứu luyện tập skill \"Sword\", ít nhất đạt đến cấp \"Sword Master\". Class khác biệt thì danh hiệu có khác đôi chút, nhưng về cơ bản là gần giống nhau.</p>" +
                            "<p id=\"256\">Hơn nữa mỗi người có có Skill hỗ trợ có được do rèn luyện, nhờ đó uy lực của skill sẽ còn tăng rõ rệt.</p>" +
                            "<p id=\"257\">Class-skill rèn luyện đến cấp cao, năng lực sẽ gia tăng rất lớn. Tất cả class-skill của ông chú đều đạt cấp \"God\", đại bộ phận skill hỗ trợ cũng đạt cấp tối đa.</p>" +
                            "<p id=\"258\">Ông chú Satoshi làm thịt thỏ một cách thần tốc với độ chuẩn xác tuyệt đối.</p>" +
                            "<p id=\"259\">“Đây… không phải trình độ của nhân loại nữa rồi. Hay là mình nên tìm chỗ ở ẩn thì tốt hơn nhỉ? Dù sao mình bây giờ cũng chẳng có liên quan gì với hai chữ bình thường…”</p>" +
                            "<p id=\"260\">Tích lũy kinh nghiệm, nâng cấp skill, sức mạnh của ông chú đã cao đến kinh người, trong game thì không sao, nhưng khi game trở thành sự thật…</p>" +
                            "<p id=\"261\">Nếu bị quốc gia nào chú ý tới, đảm bảo sẽ rất phiền phức.</p>" +
                            "<p id=\"262\">“Mình muốn tránh xa rắc rối, cũng muốn kết hôn nữa... Nhưng ai mà dám yêu loại quái vật như mình cơ chứ… haizzz…”</p>" +
                            "<p id=\"263\">Đối với loại độc thân kinh niên như ngài Đại Hiền Giả đây, vấn đề nào cũng hết sức quan trọng.</p>" +
                            "<p id=\"264\">Tuy ông chú có đủ nguyên liệu điều chế “Bí dược cả lão hoàn đồng”, nhưng tình hình bây giờ không thể nào chế tạo.</p>" +
                            "<p id=\"265\">Không có tiền cũng là cả một vấn đề.</p>" +
                            "<p id=\"266\">“Đơn vị tiền tệ cũng tương tự Nhật Bản. Quá may mắn…”</p>" +
                            "<p id=\"267\">Lục lọi tri thức trong đầu, ông chú nắm được đơn vị tiền tệ của thế giới này, nhỏ nhất là đồng tiền vàng 1 đồng, tiếp theo là 5, 10, 50, 100, 500. Kích cỡ đồng tiền thay đổi theo giá trị. 10.000.000 đồng vàng sẽ là một thỏi vàng. Các nhà giả kim liều cả mạng để luyện ra vàng.</p>" +
                            "<p id=\"268\">Nơi này khác với Trái Đất, kiếm được vàng rất dễ, nhưng rất lâu sau ông chú mới phát hiện ra điều đó.</p>" +
                            "<p id=\"269\">Rừng rậm dần bị bóng tối bao trùm.</p>" +
                            "<p id=\"270\">Khi cả thế giới dần chuyển sang thời gian của động vật ăn đêm, ông chú già cô đơn ngồi trước lửa trại, trầm tư nướng thịt thỏ… </p>" +
                            "<p id=\"271\">Dù trong cô độc, vẫn cần phải suy nghĩ.</p>" +
                            "<p id=\"272\">“Nếu giống như trong light novel, sinh mạng ở thế giới này rất rẻ tiền. Nếu có trộm cướp xuất hiện, mình sẽ giết chúng không đây?! Haiz, toàn những chuyện khiến người ta đau đầu, thôi thì, chuẩn bị tinh thần trước cũng tốt.”</p>" +
                            "<p id=\"273\">Nếu đem giả thuyết trong game và light novel áp dụng đến hiện thực, thế giới này cũng sẽ có nhiều quốc gia, mỗi quốc gia đều có thể chế cùng chủ trương khác nhau. Tùy tình huống ở quốc gia nào, cũng sẽ có đãi ngộ khác biệt.</p>" +
                            "<p id=\"274\">Có quốc gia sẽ không ưa pháp sư, có quốc gia kỳ thị bán nhân loại, có quốc gia vì tăng cường thực lực quân sự mà thực hiện chế độ nhập ngũ bắt buộc. Tuy tất cả chỉ là giả thiết, nhưng tình hình trước mắt không có gì là không thể.</p>" +
                            "<p id=\"275\">Nếu ông chú nương tay với bọn tội phạm, sau này sẽ khó sống yên, ngẫu nhiên cũng cần phải quyết đoán dứt khoát. Vì sinh tồn, tránh xa nguy hiểm, tốt nhất là không nên khiến người ta chú ý.</p>" +
                            "<p id=\"276\">“Thôi, hiện tại có nghĩ cũng vô dụng… Ăn trước đã, rốt cuộc cũng không biết bao giờ quái vật lại đến tập kích.”</p>" +
                            "<p id=\"277\">Satoshi vừa nói vừa đưa thịt lên miệng, chờ mãi thịt thỏ cũng nướng chín rồi.</p>" +
                            "<p id=\"278\">“Ngon… ngon thật… nhưng mình nhớ cơm, thèm cơm trắng quá.”</p>" +
                            "<p id=\"279\">Ông chú cô độc nơi một góc rừng già, ăn thịt nướng.</p>" +
                            "<p id=\"280\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-71e48bcc-a460-4161-8e0f-dca2fd9efb0a.jpg\" alt=\"u17587-71e48bcc-a460-4161-8e0f-dca2fd9efb0a.jpg\"></p>" +
                            "<p id=\"281\">Không nói một câu, ngấu nghiến ăn thịt thỏ săn được, bộ dạng thê thảm như người nguyên thủy.</p>" +
                            "<p id=\"282\">Nhưng ông chú vẫn phải ăn, bởi vì quá đói. Sau đó dùng dây thừng tự buộc mình lên cây cao, quyết định đi ngủ.</p>" +
                            "<p id=\"283\">Ngủ thế này sẽ an toàn hơn ngủ trên mặt đất, ông chú đoán thế, nhưng sáng sớm hôm sau mông rất đau, cho nên lại quyết định từ nay về sau sẽ không dùng đến phương pháp này nữa</p>" +
                            "<p id=\"284\">Sinh tồn nơi hoang dã, ngày thứ hai.</p>" +
                            "<p id=\"285\">“Ngủ quá khó chịu. Mông đau quá…”</p>" +
                            "<p id=\"286\">Cảm giác lời này nói ra sẽ có người hiểu lầm.</p>" +
                            "<p id=\"287\">“Hôm nay sẽ vừa săn thú, vừa luyện tập sử dụng skill. Không biết phương diện kiếm thuật thế nào? Lực lượng không thể tùy tâm sử dụng là vô nghĩa. Tệ nhất là, có khả năng sẽ vô ý giết người.”</p>" +
                            "<p id=\"288\">Vũ khí hiện tại ông chú đang sử dụng, về uy lực thật không chê vào đâu được, nói là uy lực quá thừa thì đúng hơn.</p>" +
                            "<p id=\"289\">Hai thanh kiếm treo bên hông, nhìn như tầm thường, thực ra lại là vũ khí cực kỳ nguy hiểm. Trang phục thiết kế đơn giản nên sẽ không bắt mắt, có cảm giác sẽ bị coi thường.</p>" +
                            "<p id=\"290\">Rốt cuộc bề ngoài hướng đến hình tượng là một ông chú pháp sư trung niên tầm thường.</p>" +
                            "<p id=\"291\">Trong game, cho dù không dùng đến set items mạnh nhất, ông chú vẫn cứ đánh đâu thắng đó, không gì cản nổi. Nếu trên thực tế xuất hiện loại người có sức mạnh phi thường, mọi người xung quanh sẽ sợ hãi. Bản thân Satoshi không muốn bị mọi người hâm mộ, cũng không muốn bị mọi người ghen ghét, nhưng cũng không muốn sống cô độc mãi. </p>" +
                            "<p id=\"292\">Bất kể ra sao, ông chú Satoshi đều không muốn phải cô đơn cả đời.</p>" +
                            "<p id=\"293\">Đã như vậy, phải thắng đối thủ mà không sử dụng thực lực, ra tay phải kiềm chế đúng mực an toàn, nhưng mấu chốt là ông chú không biết tiêu chuẩn cơ bản “bình thường” như thế nào.</p>" +
                            "<p id=\"294\">“Kết quả, chỉ có thể cố gắng làm quen thân thể này sao… Thật phiền toái…”</p>" +
                            "<p id=\"295\">Đã sống mười năm ở nông thôn, đã quen với sinh hoạt rề rà chậm rãi, rất ghét việc chủ động đi làm gì đó, ông chú không phải trẻ trâu, trong đầu sẽ không nổi lên ý tưởng kiểu: “Ta siêu mạnh. Ha ha ha a a a a a a a...”</p>" +
                            "<p id=\"296\">Vì ước mơ nhỏ bé, vì một gia đình bình thường, ông chú chỉ có thể cố gắng nắm giữ hoàn toàn lực lượng của mình.</p>" +
                            "<p id=\"297\">“Tìm đối thủ tốt ở đâu…”</p>" +
                            "<p id=\"298\">Vừa nói xong, ông chú liền phát hiện có sinh vật trong vòng cảm ứng \"Cảnh giới\". Skill có thể cảm ứng sinh vật hoạt động thế này thật tuyệt. </p>" +
                            "<p id=\"299\">Xào xạc…</p>" +
                            "<p id=\"300\">Lắng nghe tiếng cỏ cây ma sát, Satoshi nắm lấy chuôi kiếm bên hông.</p>" +
                            "<p id=\"301\">Quái vật quen thuộc xuất hiện, mọi người đều biết nó là Orc, thân như người mập mạp, đầu heo.</p>" +
                            "<p id=\"302\">“Thịt Orc... chắc là ăn được. Hạ nó…”</p>" +
                            "<p id=\"303\">Orc là quái vật ăn được, ăn rất ngon.</p>" +
                            "<p id=\"304\">Orc ở thế giới này cũng giống Orc trong thế giới hentai. Sức sinh sản rất mạnh, khả năng giao phối rất cao, ở trong game cũng phát triển rất đông, thường sẽ gây ra event chiến đấu quy mô lớn.</p>" +
                            "<p id=\"305\">Vừa hiếu chiến, lại ăn tạp, nên thường xuyên bị tiêu diệt, trông giống heo đi 4 chân hơn là quái vật hình người.</p>" +
                            "<p id=\"306\">Hai chân rất ngắn, hai tay giống chân trước dùng đi đường hơn là tay để sử dụng công cụ, bàn tay ba ngón vừa thô vừa to, tuy cũng có cầm công cụ, nhưng không hề linh hoạt.</p>" +
                            "<p id=\"307\">Bởi vì trông không giống người, ông chú không hề do dự dùng nó làm đồ ăn.</p>" +
                            "<p id=\"308\">Tiếp cận, rút kiếm, chém chết, tất cả diễn ra trong nháy mắt.</p>" +
                            "<p id=\"309\">“Kiềm chế rồi vẫn một nhát chết luôn… Mình còn giống quái vật hơn thì phải?”</p>" +
                            "<p id=\"310\">Con Orc có phát hiện ra kẻ địch, nhưng không kịp phản ứng, tốc độ của ông chú quá nhanh. Càng lúc càng không hiểu mình mạnh đến cỡ nào…</p>" +
                            "<p id=\"311\">Phân giải con Orc nhanh gọn, rồi lại di chuyển tiếp. Sau một thời gian lặp lại công việc tìm thấy quái vật, giết luôn, ông chú đi đến kết luận: “Mình quá mạnh. Mạnh đến không cười nổi.”</p>" +
                            "<p id=\"312\">“Tuy đồ ăn đầy đủ, nhưng chỉ có mỗi thịt…”</p>" +
                            "<p id=\"313\">Ba bữa chỉ ăn mình thịt sẽ ngán, dinh dưỡng cũng sẽ không cân đối, ông chú cố gắng đi tìm rau dại, nhưng chỉ tìm thấy các loại hạt giống cùng thảo dược. Ví dụ như \"Bloody Benladon\" - kịch độc.</p>" +
                            "<p id=\"314\">“Độc cũng có thể dùng làm thuốc, nhưng không có thiết bị, vô dụng. Tuy còn có ma thuật Magic Training, nhưng không có bình chứa chuyên dụng cho Magic Potions.”  </p>" +
                            "<p id=\"315\">Giai đoạn hiện tại, tìm được toàn thứ vô dụng.</p>" +
                            "<p id=\"316\">“Mình muốn… giá mà có bánh mì… ôi… mình nhớ cơm trắng quá…”</p>" +
                            "<p id=\"317\">Ngày thứ 3 sinh tồn nơi hoang dã, ông chú kêu gào khổ sở…</p>" +
                            "<p id=\"318\">Vốn là nhân viên văn phòng đổi nghề làm nông dân, một chút bất tiện trong sinh hoạt hàng ngày thì có thể chịu đựng, nhưng sinh tồn nơi hoang dã kiểu này quá khó chịu, cứ như người hiện đại sống cuộc sống nguyên thủy. </p>" +
                            "<p id=\"319\">Đi đường không hề gặp được người, chỉ có quái vật hung hãn coi mình là đồ ăn mà tấn công, xuất hiện liên tục, tần suất cao đến mức phải tự hỏi không biết có lẽ chết đi sẽ đỡ mệt hơn.</p>" +
                            "<p id=\"320\">Nguyên liệu nấu ăn gia tăng, thực đơn hàng ngày vẫn không đổi. ông chú bắt đầu căm ghét cuộc sống này, cả ngày đằng đằng sát khí…</p>" +
                            "<p id=\"321\">“Tại sao không tìm thấy rau dại?! Cả ngày chỉ ăn thịt, dinh dưỡng mất cân bằng…”</p>" +
                            "<p id=\"322\">Skill \"Thăm dò thực vật\" vô dụng.</p>" +
                            "<p id=\"323\">“Không thể tin tưởng lũ nữ thần đó được… Tất cả bọn chúng đều là kẻ địch a a a a a a a!”</p>" +
                            "<p id=\"324\">GRRRROOOOAAAAAAARRRRRRRRRRR</p>" +
                            "<p id=\"325\">Đây là trừng phạt do chửi thần? Cái thứ từ trên trời bay tới…</p>" +
                            "<p id=\"326\">Vảy màu xanh biếc bao trùm toàn thân, hai chân có móng vuốt sắc bén, cổ dài, miệng đầy răng nhọn.</p>" +
                            "<p id=\"327\">“F***, Wyvern!”</p>" +
                            "<p id=\"328\">Con Wyvern lì lợm bám đuôi không buông tha, lượn lờ tấn công theo kiểu hit and run, nó muốn ăn thịt ông chú Satoshi.</p>" +
                            "<p id=\"329\">Đối thủ là quái vật biết bay, thân thể thì chưa quen thuộc, ông chú đành né tránh, liên tục chạy trốn.</p>" +
                            "<p id=\"330\">Khu rừng được tắm trong hơi thở của rồng, tiếng nổ mạnh vang lên không ngừng.</p>" +
                            "<p id=\"331\">Trò chơi đuổi bắt lấy tính mạng làm phần thưởng, vẫn tiếp diễn đến khi mặt trời lặn.</p>" +
                            "")
                    .build();
            List<Chapter> chapters = new ArrayList<>();
            chapters.add(arafooChap0);
            chapters.add(arafooChap1);
            chapters.add(imotouChap);
            Chapter oregairuChap =Chapter.builder()
                    .numberTitle("Prelude")
                    .volume(oregairu)
                    .title("Và rồi, Hikigaya Komachi đã nói…")
                    .content("<p id=\"1\">Trans: Battery </p>" +
                            "<p id=\"2\">------------------------------</p>" +
                            "<p id=\"3\">------------------------------</p>" +
                            "<p id=\"4\">Tất cả chuyện này là sao chứ ?</p>" +
                            "<p id=\"5\">Ngay cả khi bạn có hỏi Komachi về những chuyện đó, Komachi cũng không thể biết…Đúng hơn là, Komachi cũng muốn tự mình hỏi anh trai.</p>" +
                            "<p id=\"6\">Cho dù komachi muốn hỏi anh trai về những điều đó, nhưng mỗi lần tôi cố gắng hỏi anh ấy, tôi không thể nói ra thành lời, tôi quá xấu hổ để hỏi về nó…</p>" +
                            "<p id=\"7\">Không, nó không phải như vậy. Không phải vì “ Onii-chan sắp bị cướp đi khỏi tôi rồi, nên tôi sẽ cô đơn chết mất, uwuwuu…”. Ah, câu đó được nhiều điểm Komachi lắm đó. Thật đó, không phải như vậy đâu!</p>" +
                            "<p id=\"8\">Tôi không nói dối bạn đâu! Bạn đang tự hỏi rằng tôi thành thật đến mức độ nào á? Tôi là một người cực kỳ trung thực. Thành thật mà nói, có lẽ onii-chan nên kết hôn nhanh nhanh đi. Đó là những gì tôi luôn nghĩ. Vâng, đúng rồi đó, tôi nói dối bạn làm gì chứ. Nó là sự thật! Tôi không hề cảm thấy cô đơn chút nào hết. Vì thế mà những gì tôi nói đều là sự thật, thật của thật.</p>" +
                            "<p id=\"9\">…Tôi đã nói NÓ LÀ SỰ THẬT rồi mà!</p>" +
                            "<p id=\"10\">Người này phiền phức thật đấy, không biết từ bỏ là gì cả! Bạn hỏi cùng một câu hỏi bao nhiêu lần rồi vậy? Bộ bố mẹ bạn là vẹt hay gì à? Ah, ra là vậy, tôi hiểu rồi! Nếu thế thì tôi chịu rồi…ai mà nghĩ tôi lại có thể gặp được nhà vô địch trong cuộc thi “người chim” cơ chứ…</p>" +
                            "<p id=\"11\">Thật lòng mà nói, không phải là tôi cảm thấy cô đơn, ghen tỵ hay gì đâu. Cũng không phải là tôi đang phủ nhận hay gì. Chỉ là tôi thật sự cảm thấy xấu hổ khi hỏi anh ấy về điều đó…</p>" +
                            "<p id=\"12\">Nó như là bạn đang hỏi bố mẹ rằng họ bắt đầu hẹn hò như thế nào ấy.</p>" +
                            "<p id=\"13\">Bạn thử tưởng tượng bạn đang ngồi ăn cơm chó của bố mẹ bạn bón cho bạn. Thật kinh khủng khi nghe những lời tán tỉnh mà bố bạn dành cho mẹ bạn. Nếu ông ấy còn làm với vẻ xấu hổ và nói với giọng điệu vô cùng xúc động thì còn tệ nữa.</p>" +
                            "<p id=\"14\">Bạn sẽ cảm thấy khó xử và bắt đầu nghịch các ngón tay của bạn. Đúng rồi đó, vẻ mặt của bạn cũng sẽ giống với Komachi lúc này.</p>" +
                            "<p id=\"15\">Hoặc là ngồi nghe anh trai của bạn kể về những câu chuyện tình yêu của anh ấy. Bạn tưởng tượng ra cảnh đó chứ? Này, bạn hiểu chứ?</p>" +
                            "<p id=\"16\">Tôi nghĩ rằng trong cuộc sống thường ngày, tôi đã luôn giữ một khoảng cách phù hợp với anh trai mình. Vì thế mà khi tôi nghe các chủ đề về những mối quan hệ, tôi vẫn có thể giữ bình tĩnh. Ế, bạn bảo chúng tôi như thế là không thân với nhau sao? Thật đấy à? Không phải tất cả các anh chị em khác đều như vậy sao? Chà, tôi cũng không chắc nữa.</p>" +
                            "<p id=\"17\">Chắc vì vậy mà cho tới tận bây giờ, tôi không gặp vấn đề gì khi nghe mấy chuyện này. Hay tại vì lúc đó tôi không hiểu mấy thứ đó ta. Dù sao thì tôi cũng cảm thấy nó không chân thực một chút nào.</p>" +
                            "<p id=\"18\">…Ah, không không, không phải tôi chưa bao giờ tưởng tượng về nó, đúng hơn là tôi chưa tưởng tượng đủ. Một khung cảnh mơ hồ kiểu “Em vui lắm! Em hạnh phúc lắm! Onee-chan!” là những gì mà tôi hay tưởng tượng ra.</p>" +
                            "<p id=\"19\">Nhưng thực tế thì không đơn giản như vậy…</p>" +
                            "<p id=\"20\">Sao cơ? Nói lại những gì tôi vừa nói á? “Em vui lắm! Em hạnh phúc lắm! Onee-chan!” như thế này á?</p>" +
                            "<p id=\"21\">Một lần nữa? Đây có phải là phòng tắm hơi đâu mà muốn một lần nữa? Nhưng không sao, tôi sẽ nói lại một lần nữa. Dù gì thì Komachi cũng đang có tâm trạng tuyệt vời mà.</p>" +
                            "<p id=\"22\">One, two, three, Onee-chan!</p>" +
                            "<p id=\"23\">Whew …</p>" +
                            "<p id=\"24\">Có chuyện gì với người này vậy…à, cho tôi chút trà trước đã.</p>" +
                            "<p id=\"25\">… Wheeew …</p>" +
                            "<p id=\"26\">Ah, xin lỗi, chúng ta tiếp tục nào. Dù chẳng có lý do gì để komachi phải xin lỗi cả…sao cũng được. Để chuyện đó sang một bên đi.</p>" +
                            "<p id=\"27\">Tôi nhớ có một lần onii-chan cũng cố nói chuyện này ra. Anh ấy hứa sẽ kể với tôi nếu có gì đó xảy ra.</p>" +
                            "<p id=\"28\">Nhưng, con người anh ấy là vậy mà. Ngay cả khi tôi hỏi anh ấy, anh ấy cố gắng che giấu và bào chữa. Vì vậy tôi sẽ tra hỏi một cách chậm rãi, rồi anh ấy cũng từ từ tiết lộ chúng từng chút một. Bằng cách đó, Komachi cũng có thể chấp nhận thực tại từng chút một. Đó là những gì tôi đã nghĩ…</p>" +
                            "<p id=\"29\">Nhưng mọi thứ không như tôi đã tưởng tượng.</p>" +
                            "<p id=\"30\">Không, ban đầu là tôi chỉ thuận theo anh ấy, trêu anh ấy một chút để có thể lấy thêm thông tin. Nhưng mỗi khi tôi hỏi “Sao thế, vậy là hai người thực ra là đang hẹn hò hả?” thì cuộc trò chuyện lại đi vào bế tắc.</p>" +
                            "<p id=\"31\">Bởi vì, đó là onii-chan! Onii-chan của tôi đó!</p>" +
                            "<p id=\"32\">Bạn biết chuyện gì sẽ xảy ra tiếp theo không? Onii-chan với vẻ mặt nghiêm túc và hắng giọng nói với tôi một cách nghiêm túc: “Komachi, thực ra có một số chuyện anh muốn nói với em…”</p>" +
                            "<p id=\"33\">Tôi đã nghĩ anh ấy sẽ nói về nó, cuối cùng anh ấy cũng nói với tôi về chuyện đó!</p>" +
                            "<p id=\"34\">Vì thế mà Komachi ngay lập tức ngồi thẳng lưng lên.</p>" +
                            "<p id=\"35\">Tôi dùng cả thanh xuân để đợi anh ấy nhưng cuối cùng anh ấy vẫn chẳng chịu nói ra.</p>" +
                            "<p id=\"36\">Và cuối cùng, tất cả những gì anh ấy nói được nghe còn chẳng rõ ràng: “ừm, thực ra là, anh nghĩ mọi chuyện vẫn đang diễn ra khá tốt”. Tai ảnh đỏ chót, mắt đảo quanh liên tục, rồi thở dài trong khi gượng cười.</p>" +
                            "<p id=\"37\">Tôi thậm chí còn không biết anh ấy như thế nào nữa, kinh tởm? dễ thương? Hay là trong sáng?</p>" +
                            "<p id=\"38\">Tôi cũng không biết liệu anh ấy có đang hạnh phúc hay không. Đến cả tôi cũng bắt đầu cảm thấy xấu hổ rồi đây này.</p>" +
                            "<p id=\"39\">Tôi không biết phải đáp lại như thế nào ngoài “Ồ, ồ, ra là như vậy, thật tuyệt, cuối cùng Komachi thấy yên tâm rồi”, tôi giả vờ bình tĩnh và cuộc hội thoại kết thúc. Ôi trời, nó thực sự làm cho tôi ngạc nhiên, không ngờ tôi lại có tài năng diễn xuất như vậy. Nếu không tự kiềm chế bản thân, tôi có thể đã trở thành người phụ nữ độc ác chuyên đi chơi đùa với trái tim của những người đàn ông mất.</p>" +
                            "<p id=\"40\">Hmm? Bạn đang nói rằng chúng tôi giống nhau về cách mà chúng tôi giải quyết vấn đề của mình? Không không không, không hề có sự tương đồng nào ở đây, không một chút nào. Nếu bạn tiếp tục nói như thế, tôi sẽ giận đấy!</p>" +
                            "<p id=\"41\">Bởi vì chúng tôi không giống nhau một chút nào! Onii-chan giải quyết các vấn đề của mình bằng cách tự hạ thấp bản thân của mình một cách kỳ lạ khiên nó trong thật kinh tởm. Nhưng cách làm của Komachi có một sức mạnh khó tả, nó đi kèm với sự dễ thương và một chút tinh tế…Bạn đang cười cái gì vậy hả! Uh…không, như tôi đã nói, chúng tôi không giống nhau.</p>" +
                            "<p id=\"42\">Bạn nói tôi cũng như vậy á? Cái đó thì tôi thừa nhận</p>" +
                            "<p id=\"43\">Chúng tôi có cùng suy nghĩ.</p>" +
                            "<p id=\"44\">Đúng vậy, Komachi lại vô tình che giấu vấn đề này một lần nữa rồi.</p>" +
                            "<p id=\"45\">Tôi nghĩ onii-chan cũng như vậy.</p>" +
                            "<p id=\"46\">Nó giống như là một cái gai mắc kẹt trong trái tim của anh ấy vậy. Có những điều mà anh ấy không muốn bộc lộ ra ngoài vì vậy mà anh ấy không thể chuyển nó thành lời. Đó là tại sao anh ấy không nói cho Komachi biết nhiều hơn,</p>" +
                            "<p id=\"47\">Nhưng đó chỉ là những suy nghĩ ích kỉ của tôi…</p>" +
                            "<p id=\"48\">Ah, không, chẳng có bằng chức xác thực nào. Rốt cuộc thì, Komachi thậm chí còn không ở bên anh ấy, không lắng nghe tất cả những gì anh ấy nói, và vẫn còn nhiều điều chưa hiểu về anh ấy.</p>" +
                            "<p id=\"49\">Tuy nhiên, trong những khoảng thời gian sinh hoạt ở phòng câu lạc bộ, tôi lại nghĩ rằng “Ah, onii-chan lại mơ giữa ban ngày nữa rồi”.</p>" +
                            "<p id=\"50\">Bạn biết không, khi onii-chan mơ tưởng gì đó, anh ấy thường nghĩ về những thứ rất khó hiểu và mặt anh ấy trở thành một mớ hỗn độn.</p>" +
                            "<p id=\"51\">Đúngggg! Như là mặt bạn lúc ăn phải một viên kẹo chua vậy.</p>" +
                            "<p id=\"52\">Ah, vậy là bạn đã biết. Bạn thông minh hơn vẻ ngoài của bạn đấy.</p>" +
                            "<p id=\"53\">Yeah, kiểu như, sẽ ổn thôi nếu anh ấy cau mày như bình thường, nhưng khi ấy ấy mơ tưởng thì toàn bộ mặt anh ấy toát lên vẻ chua chát và khó chịu.</p>" +
                            "<p id=\"54\">Những thứ như thế này, nếu như bạn không ở cùng nhau trong khoảng thời gian dài, bạn sẽ không thể biết về nó…Đó là những gì tôi nghĩ. Ồ thật ư? Có những người đã biết về điều này ngoài Komachi á?</p>" +
                            "<p id=\"55\">Oh ok.</p>" +
                            "<p id=\"56\">Chà, vậy là mọi người đã biết hết…có vẻ như Komachi đã nói một số thứ không cần thiết rồi.</p>" +
                            "<p id=\"57\">…Ah!</p>" +
                            "<p id=\"58\">Aaah, dừng lại đi, đừng xoa đầu tôi nữa! tôi đã tốn rất nhiều công sức để giữ cho mái tóc gọn gàng đấy…có vẻ như người này dùng rất nhiều lực khi xoa đầu ai đó…ouch, bỏ tay ra khỏi đầu tôi đi! Hmm, điều này không thực sự quá tệ.</p>" +
                            "<p id=\"59\">…Đúng, bạn đã đúng.</p>" +
                            "<p id=\"60\">Thật vậy, ngay cả khi Komachi không làm gì, những người khác sẽ làm điều gì đó, tôi nghĩ thế.</p>" +
                            "<p id=\"61\">Heh-heh, khi nghe bạn nói vậy, tôi nghĩ tâm trạng tôi đã khá hơn chút đó.</p>" +
                            "<p id=\"62\">Ah! Chờ đã! Tôi đã nói là đừng xoa đầu tôi nữa mà! Không, tôi không buồn đến mức đó!</p>" +
                            "<p id=\"63\">Nhưng tương lai khiến tôi thực sự cảm thấy lo lắng, ahhhh…thật là một tương lai rắc rối…chỉ cần nghĩ rằng nó sẽ xảy ra từ ngay bây giờ, khiến Komachi thực sự lol lắng mà.</p>" +
                            "<p id=\"64\">Đúng, như những gì bạn nghĩ.</p>" +
                            "<p id=\"65\">Điều này có lẽ sẽ còn kéo dài trong một khoảng thời gian.</p>" +
                            "<p id=\"66\">Nhưng tôi nghĩ mọi thứ sẽ dần thay đổi.</p>" +
                            "<p id=\"67\">Không cần biết đó là Komachi hay onii-chan.</p>" +
                            "<p id=\"68\">…hay là tất cả mọi người trong câu lạc bộ tình nguyện.</p>" +
                            "<p id=\"69\">Cuộc sống mới của tôi? Yeah, nó khá là thuận lợi. Tôi đang làm việc chăm chỉ, chậm mà chắc.</p>" +
                            "<p id=\"70\">Còn về câu lạc bộ…? Mọi thứ có vẻ là tốt…không quá tốt nhưng cũng không đến nỗi là tệ.</p>" +
                            "<p id=\"71\">Vậy nên, nếu bạn có thể tiếp tục dõi theo anh ấy thêm một khoảng thời gian nữa, tôi sẽ rất hạnh phúc với tư cách là một người em gái. Ah, câu đó cũng đạt điểm Komachi cao đó!</p>" +
                            "<p id=\"72\">Sau đó, nếu có bất kỳ điều gì khác xảy ra, tôi sẽ cho bạn biết.</p>" +
                            "<p id=\"73\">Chuyện là như vậy đó…</p>" +
                            "<p id=\"74\">Từ nay về sau vẫn mong được mọi người giúp đỡ nhé!</p>\n")
                    .build();
            chapters.add(oregairuChap);
            chapterRepository.saveAll(chapters);
            Novel arafooLn =arafoo.getNovel();
            arafooLn.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            Novel oregairuLn =oregairu.getNovel();
            oregairuLn.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            Novel imotouLn =imotou.getNovel();
            imotouLn.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            novelRepository.save(arafooLn);
            novelRepository.save(imotouLn);
            novelRepository.save(oregairuLn);
        }
    }

    private void addVol() {
        if (volumeRepository.findAll().isEmpty()) {
            List<Volume> volumes = new ArrayList<>();
            Novel arafoo = novelRepository.findById("NOVEL-1").get();
            Novel imotou = novelRepository.findById("NOVEL-2").get();
            Novel oregairu = novelRepository.findById("NOVEL-11").get();
            volumes.add(Volume.builder()
                    .title("Tập 1").novel(arafoo)
                    .build());
            volumes.add(Volume.builder()
                    .title("Volume 1").novel(imotou)
                    .build());
            volumes.add(Volume.builder()
                    .title("Tập 1").novel(oregairu)
                    .build());
            volumeRepository.saveAll(volumes);
        }
    }

    private void addNovel() {
        if (novelRepository.findAll().isEmpty()) {
            List<Novel> novels = new ArrayList<>();
            Account account = accountRepository.findByUsername("admin").get();
            Author author = authorRepository.findByName("Kotobuki Yasukiyo").get();
            Artist artist = artistRepository.findByName("TEDDY").get();
            List<String> typesName = new ArrayList<>();
            typesName.add("Action");
            typesName.add("Adventure");
            typesName.add("Comedy");
            typesName.add("Drama");
            typesName.add("Isekai");
            List<Type> types = typeRepository.findByNameIn(typesName);
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s3838-2f4279f7-6dad-41e0-99d1-592c760f850d-m.jpg")
                    .name("Arafoo Kenja No Isekai Seikatsu Nikki")
                    .otherName("Around 40 Kenja no Isekai Seikatsu Nikki, アラフォー賢者の異世界生活日記, Hiền giả 40 tuổi chuyển sinh đến thế giới khác")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("Ông chú trung niên chết đi với vị thế top player trong game, kẻ đã hạ gục boss cuối Evil God.\n" +
                            "\n" +
                            "Chuyển sinh sang thế giới khác cùng sức mạnh overpower giết chết cả thánh thần.\n" +
                            "\n" +
                            "Bắt đầu hành trình mới nơi thế giới mới.\n" +
                            "\n" +
                            "Thế giới mới với nền văn minh ma pháp lạc hậu, hãy chuẩn bị run rẩy, cúi đầu trước bước chân chinh phạt của hiền giả FA 40 tuổi.\n" +
                            "\n" +
                            "\"Ờh, tôi chỉ muốn kiếm mảnh vườn để trồng trọt chăn nuôi sống yên lành thôi ! Có bánh mì ăn là hạnh phúc rồi...\"")
                    .author(author)
                    .artist(artist)
                    .types(types)
                    .extraNote("<p> I am the bone of my project<br> Time is my body and delay is my blood<br> Unknown to Donate,<br> Nor known to Money.<br> Yet, those hands will never hold anything<br> So as I pray, Unlimited Delay Time.</p>\n" +
                            "<p>==========</p>\n" +
                            "<p>Ai đọc rồi hay chưa ta không cần biết, đọc convert hay đọc Eng hay vã được tiếng Trung tiếng Nhật gì ta cũng éo quan tâm, nhưng thích spoil tự lập project riêng ấy, éo ai quản!<br>Chỗ của ta cấm spoil ok?</p>\n" +
                            "<p>==========</p>\n" +
                            "<p>Không nhận Donate dưới mọi hình thức</p>\n" +
                            "<p>Làm vì hứng thú</p>\n" +
                            "<p>30 ngày 1 chương hay là 3 ngày 1 chương cũng là tùy hứng</p>\n" +
                            "<p>==========</p>\n" +
                            "<p>Tác phẩm có các thuộc tính ẩn như sau: Loli, Big Boobs, Gender Bender, Harem, Oldman...</p>\n" +
                            "<p>Ông chú già khó tính solo dịch</p>\n" +
                            "<p>Già và chậm, không giục</p>\n" +
                            "<p>Edit chưa ngon sẽ không up, lỗi chính tả sẽ không up, đọc chưa mượt sẽ không up</p>\n" +
                            "<p>Muốn bomb thì phải nhịn vài ngày không chương nhá <img src=\"../../../img/emoticon/yahoo/21.gif\" alt=\"21.gif\"></p>\n" +
                            "<p>Thích ăn vặt hàng ngày hay nhịn vài ngày chén 1 bữa ??? <img src=\"../../../img/emoticon/yahoo/21.gif\" alt=\"21.gif\"></p>\n" +
                            "<p>Tiếp nhận và xem xét mọi ý kiến góp ý</p>\n" +
                            "<p>Thật sự khi đọc lại vẫn thấy gượng, mà để sát ý nhất nên không nghĩ được cách sửa</p>\n" +
                            "<p>Nguồn:</p>\n" +
                            "<p>WN: <a href=\"https://ncode.syosetu.com/n8515dc/\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://ncode.syosetu.com/n8515dc/</a></p>\n" +
                            "<p>LN: <a href=\"https://bszip.com/novel-52509.html\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://bszip.com/novel-52509.html</a></p>\n" +
                            "<p>Đặt mua ủng hộ tác giả tại đây <a href=\"http://mfbooks.jp/books/series/around/\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">http://mfbooks.jp/books/series/around/</a></p>")
                    .translator(account)
                    .translationStatus(TranslationStatus.PAUSE)
                    .build());
            //novel 2
            List<String> typesName2 = new ArrayList<>();
            typesName2.add("Slice of Life");
            typesName2.add("Adapted to Anime");
            typesName2.add("Comedy");
            typesName2.add("Drama");
            typesName2.add("Romance");
            List<Type> types2 = typeRepository.findByNameIn(typesName2);
            Author author2 = authorRepository.findByName("Yomi Hirasaka").get();
            Artist artist2 = artistRepository.findByName("Kantoku").get();
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s12084-45befb49-f4c4-42c9-86b7-d0e23a980557-m.jpg")
                    .name("Imouto sae Ireba Ii")
                    .otherName("A Sister's All You Need.")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("Truyện xoay quanh cuộc sống thường ngày của Itsuki Hashima, một tiểu thuyết gia cuồng em gái hạng nặng. Sở thích có phần 'điên khùng' cùng mong ước có một đứa em gái của cậu đã dẫn đến những tình huống hài hước, lãng mạn nhưng không kém phần sâu sắc xuyên suốt các tập truyện. ")
                    .extraNote("<p>Trình độ tiếng Nhật của trans chỉ ở mức \"Arigathanks, Sorrymasen, Chotto minute\" nên truyện được dịch từ bản Eng.</p>\n" +
                            "<p><a href=\"https://jnovels.com/download-a-sisters-all-you-need-light-novel-pdf/?fbclid=IwAR1KfrX5ixCZBi46x8iO3sgpjTMpo9mdPQQ-jbgnH4Xn_ZpaF2tyzlj3IQo\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">Nguồn Light Novel</a></p>\n" +
                            "<p>Đồng thời xin cảm ơn bạn <a href=\"https://www.facebook.com/Blank.428\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">Blank.428</a> đã lên màu những tấm hình minh họa vô cùng chất lượng.</p>\n" +
                            "<p>Chúc mọi người đọc truyện vui vẻ.</p>")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(author2)
                    .artist(artist2)
                    .types(types2)
                    .translator(account)
                    .build());

            //novel 3
            List<String> typesName3 = new ArrayList<>();
            typesName3.add("Action");
            typesName3.add("Adventure");
            typesName3.add("Ecchi");
            typesName3.add("Drama");
            typesName3.add("Fantasy");
            typesName3.add("Mature");
            List<Type> types3 = typeRepository.findByNameIn(typesName3);
            Author densuke = authorRepository.save(Author.builder().name("Densuke").build());
            Artist ban = artistRepository.save(Artist.builder().name("Ban").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s12217-077c5170-dbce-4fae-a215-49028288e266-m.jpg")
                    .name("Yondome wa Iyana Shi Zokusei Majutsushi")
                    .otherName("The Death Mage Who Doesn't Want A Fourth Time")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Một vụ tai nạn thương tâm khiến cho hơn một trăm người xấu số bỏ mạng. Khi tất cả tỉnh dậy, họ được vị thần luân hồi đề nghị tái sinh sang thế giới khác.</p>\n" +
                            "<p>Amamiya Hiroto, cậu học sinh nằm trong những người được tái sinh đã không may bị tái sinh khi chẳng nhận được chút sức mạnh nào. Để rồi cuộc đời thứ hai của cậu còn khốn khổ hơn cả kiếp trước.</p>\n" +
                            "<p>Sau khi kết thúc cuộc đời thứ hai trong cay đắng. Vị thần luân hồi cho cậu biết rằng sẽ còn lần thứ ba và thứ tư. Nhưng vì không muốn cậu phải chịu thêm bất hạnh ở cuộc đời thứ ba, ông ta đã nguyền rủa cậu với hy vọng cậu sẽ nhanh chóng tự sát.</p>\n" +
                            "<p>Ở lần tái sinh tiếp theo, Hiroto là một cá thể Dhampir, một giống loài với một nửa dòng máu Vampire, một nửa là Dark Elf. Không muốn làm theo lời gã thần kia, cậu đã quyết định sẽ sống sót bằng bất cứ giá nào. </p>")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(densuke)
                    .artist(ban)
                    .types(types3)
                    .translator(account)
                    .build());
            //novel 4
            List<String> typesName4 = new ArrayList<>();
            typesName4.add("Slice of Life");
            typesName4.add("Adapted to Anime");
            typesName4.add("Comedy");
            typesName4.add("Drama");
            typesName4.add("Romance");
            List<Type> types4 = typeRepository.findByNameIn(typesName4);
            Author nekoyasha = authorRepository.save(Author.builder().name("Nekoyasha").otherName("猫夜叉").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s8980-f7aa1974-05fb-48af-9cc3-8c81940717be-m.jpg")
                    .name("Shin no Jitsuryoku wa Girigiri Made Kakushite Iyou to Omou")
                    .otherName("I Think I’ll Hide My True Ability to the Last Moment, 真の実力はギリギリまで隠していようと思う")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Trong một thế giới mà khi lập giao ước với các vị thần, bạn sẽ được ban tặng những kĩ năng, Yuno được sinh ra là con thứ ba của tại một gia tộc kị sĩ danh giá. Tuy nhiên, vì sinh ra vơi quá nhiều sức mạnh nên cậu đã sớm phải kiềm hãm bản thân lại lại và được cho là bất tài. Tuy là thế, Yuno vẫn cảm thấy hoàn toàn ổn với mọi chuyện. Cậu nhận thức được mầm mống tai họa chính sức mạnh của mình có thể đem lại. Tuy nhiên, một cơ hội tại Học viện Ma pháp Kị Sĩ Feliz đã dẫn đến cuộc gặp gỡ định mệnh giữa cậu và một vị thần vô danh. Như cậu, cô cũng là người bị ghẻ lạnh xung quanh vì sự bất tài.</p>\n" +
                            "<p>Đây là câu truyện về một cậu bé đã lập khế ước với một vị thần vô danh và hết lòng đưa cô lên trở thành một vị thần tối cao.</p>")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(nekoyasha)
                    .types(types4)
                    .translator(account)
                    .build());

            //novel 5
            List<String> typesName5 = new ArrayList<>();
            typesName5.add("Action");
            typesName5.add("Adventure");
            typesName5.add("Comedy");
            typesName5.add("Fantasy");
            typesName5.add("Isekai");
            typesName5.add("Drama");
            typesName5.add("Magic");
            typesName5.add("Supernatural");
            List<Type> types5 = typeRepository.findByNameIn(typesName5);
            Author nekoko = authorRepository.save(Author.builder().name("Nekoko").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s447-f0a304f0-7ce4-4255-ad00-7b8a701614b6-m.jpg")
                    .name("Tensei shitara doragon no tamago datta ~ saikyō igai mezasane ~e ~")
                    .otherName("Reincarnated as a Dragon’s Egg ~ Lets Aim to Be the Strongest ~,Tái sinh là một quả trứng rồng ~ Hướng về mục tiêu trở thành kẻ mạnh nhất ~")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Tôi tỉnh đậy ở một khu rừng vô danh trong hình dạng một quả trứng.</p>\n" +
                            "<p>Nơi này giống như một thế giới giả tưởng, những sinh vật kỳ lạ và hung hãn khắp xung quanh. Giống như trong game, dường như tôi có thể kiểm tra chỉ số của tôi và kẻ thù.</p>\n" +
                            "<p>Tôi muốn săn những con quái vật, thu thập danh hiệu để lên cấp và tiến hóa trở thành một con rồng mạnh mẽ hơn.</p>\n" +
                            "<p>Rồi tôi nghe thấy một giọng nói bí ẩn ở trong đầu mình: “Hãy hướng đến mục tiêu trở thành kẻ mạnh nhất!”</p>")
                    .extraNote("<p>Link Eng (chap 219 trở đi do những nhóm Eng trước kia đều drop và mất dạng):</p>\n" +
                            "<p><a href=\"https://cheldra.wordpress.com/reincarnated-as-a-dragons-egg-%EF%BD%9Elets-aim-to-be-the-strongest%EF%BD%9E/\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://cheldra.wordpress.com/reincarnated-as-a-dragons-egg-%ef%bd%9elets-aim-to-be-the-strongest%ef%bd%9e/</a></p>\n" +
                            "<p>Link raw Nhật:</p>\n" +
                            "<p><a href=\"http://ncode.syosetu.com/n4698cv/\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">http://ncode.syosetu.com/n4698cv/</a></p>\n" +
                            "<p>Link Wiki:</p>\n" +
                            "<p><a href=\"https://doratama.fandom.com/wiki/DoraTama_Wiki\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">DoraTama Wiki | Fandom</a></p>\n" +
                            "<p>Tài khoản <a href=\"https://twitter.com/nekoko1228\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">Twitter</a> của tác giả.</p>\n" +
                            "<p>Còn đây là <a href=\"https://twitter.com/phcom0101\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">Twitter</a> của tui :))</p>\n" +
                            "<p>Lịch up: Ra tới đâu hay tới đâu :v</p>\n" +
                            "<p>Mời mọi người thảo luận chém gió tại đây:<br><a href=\"https://hako.re/forum/23-thao-luan-light-novel/33553-thao-luan-tensei-shitara-dragon-no-tamago-data-saikyou-igai-mezasanee.html\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://hako.re/forum/23-thao-luan-light-novel/33553-thao-luan-tensei-shitara-dragon-no-tamago-data-saikyou-igai-mezasanee.html</a></p>")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(nekoko)
                    .types(types5)
                    .translator(account)
                    .build());

            //novel 6
            List<String> typesName6 = new ArrayList<>();
            typesName6.add("Action");
            typesName6.add("Adventure");
            typesName6.add("Comedy");
            typesName6.add("Fantasy");
            typesName6.add("Isekai");
            typesName6.add("Drama");
            typesName6.add("Magic");
            typesName6.add("Supernatural");
            List<Type> types6 = typeRepository.findByNameIn(typesName6);
            Author shuu = authorRepository.save(Author.builder().name("Shuu").otherName("秋").build());
            Artist shizuma_Yoshinori = artistRepository.save(Artist.builder().name("Shizuma Yoshinori").otherName("秋").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s3601-c1361136-16b0-4692-b703-c8a5e0da67a0-m.jpg")
                    .name("Maou Gakuin no Futekigousha ~Shijou Saikyou no Maou no Shiso, Tensei shite Shison tachi no Gakkou e Kayou~")
                    .otherName("Học viện ma vương, Kẻ không phù hợp ở Học viện quỷ vương ~Quỷ vương thuỷ tổ mạnh nhất lịch sử, chuyển sinh và đi học cùng con cháu~, " +
                            "The Student Unsuited for Demon Lord School ~ The Strongest Demon Lord in History, the Founder, Reincarnates and Attends a School With His Descendants ~, " +
                            "魔王学院の不適合者～史上最強の魔王の始祖、転生して子孫たちの学校へ通う～")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Trong một thế giới mà khi lập giao ước với các vị thần, bạn sẽ được ban tặng những kĩ năng, Yuno được sinh ra là con thứ ba của tại một gia tộc kị sĩ danh giá. Tuy nhiên, vì sinh ra vơi quá nhiều sức mạnh nên cậu đã sớm phải kiềm hãm bản thân lại lại và được cho là bất tài. Tuy là thế, Yuno vẫn cảm thấy hoàn toàn ổn với mọi chuyện. Cậu nhận thức được mầm mống tai họa chính sức mạnh của mình có thể đem lại. Tuy nhiên, một cơ hội tại Học viện Ma pháp Kị Sĩ Feliz đã dẫn đến cuộc gặp gỡ định mệnh giữa cậu và một vị thần vô danh. Như cậu, cô cũng là người bị ghẻ lạnh xung quanh vì sự bất tài.</p>\n" +
                            "<p>Đây là câu truyện về một cậu bé đã lập khế ước với một vị thần vô danh và hết lòng đưa cô lên trở thành một vị thần tối cao.</p>")
                    .extraNote("<p>Sau một thời gian dài đối địch với con người, tinh linh và cả những vị thần, trải qua vô vàn trận chiến và xung đột, Quỷ vương Arnos đã trở nên chán ngấy và mệt mỏi với tất cả những điều đó, và bắt đầu mong mỏi một thế giới hòa bình. Vì vậy, gã quỷ vương ấy đã quyết định hi sinh thân mình để xây dựng bức tường phân chia thế giới, chấm dứt cuộc chiến tranh, và chuyển sinh đến 2000 năm sau.</p>\n" +
                            "<p>Tuy nhiên, những gì chờ đợi cậu ta sau khi tái sinh là một thế giới vì quá quen với sự bình an mà con cháu của cậu trở nên quá yếu ớt , ma thuật sụt giảm nghiêm trọng, và những đại ma pháp thì hầu như biến mất.</p>\n" +
                            "<p>Arnos theo học tại Học viện Quỷ vương - nơi được thành lập để tìm bất kỳ học viên nào có thể là tái sinh của quỷ vương, nhưng khả năng của cậu ta quá phi thường đến mức những người trong học viện không thể đánh giá đúng khả năng của cậu ta, vì vậy Arnos bị mọi người gắn cho cái mác là “Kẻ không phù hợp”. Bị đánh giá thấp và xa lánh bởi hầu hết mọi người ở đó, nhưng rồi cậu cũng đã gặp được một cô gái có thái độ thân thiện với mình, Misha, là thuộc hạ, cũng như đồng chí đầu tiên của cậu trong thời đại này. Cả hai cùng chung một mục tiêu, phấn đấu leo lên đỉnh của hệ thống phân cấp quỷ để một ngày không xa đòi lại danh hiệu và địa vị đã từng là của cậu.</p>\n")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(shuu).artist(shizuma_Yoshinori)
                    .types(types6)
                    .translator(account)
                    .build());

            //novel 7
            List<String> typesName7 = new ArrayList<>();
            typesName7.add("Slice of Life");
            typesName7.add("Adapted to Anime");
            typesName7.add("Comedy");
            typesName7.add("Drama");
            typesName7.add("Romance");
            List<Type> types7 = typeRepository.findByNameIn(typesName7);
            Author rifujin_na_Magonote = authorRepository.save(Author.builder().name("Rifujin na Magonote").otherName("猫夜叉").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s8980-f7aa1974-05fb-48af-9cc3-8c81940717be-m.jpg")
                    .name("Mushoku Tensei - Isekai Ittara Honki Dasu")
                    .otherName("Thất nghiệp chuyển sinh")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Một otaku vô công rồi nghề 34 tuổi bị đuổi ra khỏi nhà bởi chính gia đình của mình. Nhận ra cuộc đời của bản thân đã lâm vào ngõ cụt cũng như là sự rác rưởi, vô dụng của bản thân; anh ta ước rằng phải chi bản thân khi xưa vượt qua được giai đoạn đen tối của cuộc đời thì bây giờ có lẽ mọi chuyện đã khác. Đúng vào khoảnh khắc hối tiếc đó, anh thấy 1 chiếc xe tải chạy với vận tốc lớn đang lao đến 3 học sinh trung học gần đó. Gom tất cả sức lực còn lại, anh ta cứu được 3 học sinh kia tuy nhiên lại phải bỏ mạng của chính bản thân mình dưới bánh chiếc xe tải đó. Khi mở mắt ra, anh nhận ra rằng mình đã được đầu thai ở thế giới của gươm giáo và phép thuật song hành tồn tại dưới cái tên Rudeus Greyrat. Dưới hình hài mới ở một thế giới mới, Rudeus tự khẳng định với bản thân \" Lần này mình sẽ thực sự sống đến tận cùng cuộc sống này mà không hề tiếc nuối\". Và như thế, cuộc hành trình của anh bắt đầu.</p>\n")
                    .extraNote("<p><em>- <strong>WN</strong> là bản truyện tác giả đăng miễn phí trên mạng, không có hình minh họa. (<strong>Hiện người dịch đang dịch bản này</strong>)</em><br><em>- <strong>LN</strong> là bản truyện xuất bản giấy đã được trau chuốt từ bản WN, có hình minh họa.</em><br><em><br></em><u>Lưu ý:</u><em><br></em></p>\n" +
                            "<p>- Từ khoảng Volume 16 trở đi <strong>người dịch không còn dịch từ bản tiếng anh nữa</strong> vì bản này dịch sai lệch nghĩa rất nhiều.<strong><br>-</strong> <strong>Không tiết lộ nội dung quan trọng ở mục bàn luận</strong>, hãy bàn ở chương có nội dung tương ứng. <br><strong>-</strong> <strong>Không đề cập đến bản manga.<br></strong>Mọi vi phạm sẽ bị xóa không báo trước.</p>\n")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(rifujin_na_Magonote)
                    .types(types7)
                    .translator(account)
                    .build());

            //novel 8
            List<String> typesName8 = new ArrayList<>();
            typesName8.add("Slice of Life");
            typesName8.add("Adapted to Anime");
            typesName8.add("Comedy");
            typesName8.add("Drama");
            typesName8.add("Romance");
            List<Type> types8 = typeRepository.findByNameIn(typesName8);
            Author yuu_Tanaka = authorRepository.save(Author.builder().name("Yuu Tanaka").otherName("猫夜叉").build());
            Artist flo = artistRepository.save(Artist.builder().name("Flo").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s787-8ae84f4f-4cc8-4acd-914d-72682df9e28e-m.jpg")
                    .name("Tensei Shitara Kendeshita")
                    .otherName("Chuyển sinh thành kiếm, 転生したら剣でした")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Khi nhận ra thì ... tôi đang ở 1 thế giới khác. Tôi đã trở thành 1 thanh kiếm, Dafuq? Chuyện gì thế này? Chỗ tôi tỉnh dậy sao lại toàn quái vật thế này. Tôi liền bay đi kiếm ngay 1 cộng sự cho mình (tuyển gái only, anh hùng đẹp trai mời đi chỗ khác). Một viên ma pháp thạch à? Tôi có được kĩ năng mới nhờ hấp thụ nó? Có vẻ hay đấy, mau đưa tôi thêm nào! Ừ tất nhiên là nhà ngươi sẽ không cho rồi, nhưng ta vẫn sẽ lấy nó, hahaha!</p>\n" +
                            "<p>A, bị kẹt rồi. Trò đùa gì thế này? Ha, cô bé tai mèo đằng kia ơi, kéo anh ra với! Hể? Em đang bị ma thú tấn công sao!? Được lắm, cứ để đó cho anh! Cơ mà kéo anh ra cái đã!</p>\n" +
                            "<p>Đây là câu chuyện của tôi, người không hiểu do đâu mà tái sinh thành một thanh kiếm, cùng phiêu lưu với một thiếu nữ tai mèo và cùng cô bé trưởng thành hơn từng ngày.</p>\n")
                    .extraNote("<p>Dịch:</p>\n" +
                            "<p>Từ chapter 1 đến 14:</p>\n" +
                            "<p><a href=\"https://www.facebook.com/Hearter.Zouest\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://www.facebook.com/Hearter.Zouest</a> (Hoàng Lê)</p>\n" +
                            "<p><a href=\"https://www.facebook.com/omega.hoang.9\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://www.facebook.com/omega.hoang.9</a> (Yang)</p>\n" +
                            "<p>Nghỉ:</p>\n" +
                            "<p><a href=\"https://www.facebook.com/gkhoa\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://www.facebook.com/gkhoa</a> (Mắm)</p>\n" +
                            "<p><a href=\"https://www.facebook.com/nv.cuong1999\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://www.facebook.com/nv.cuong1999</a> (Nguyen Viet Cuong)</p>\n" +
                            "<p><a href=\"https://www.facebook.com/profile.php?id=100049667441269\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://www.facebook.com/profile.php?id=100049667441269</a> (Nguyễn Tùng Lâm)</p>\n" +
                            "<p>Hiện tại:</p>\n" +
                            "<p><a href=\"https://www.facebook.com/ColorsSyndrome\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://www.facebook.com/ColorsSyndrome</a> (Fakebi)</p>\n" +
                            "<p>___</p>\n" +
                            "<p>Ủng hộ dịch giả... thật ra mọi người cmt thôi là mình vui rồi, nên mọi người không gửi tiền ủng hộ thì trans vẫn làm nhé :v <br>Và rất cảm ơn các bạn ủng hộ nha (´ ∀ ` *)</p>\n" +
                            "<p>Chủ khoản: Nguyễn Hữu Thịnh<br>Ngân hàng: Vietinbank<br>Stk: 109869005839</p>\n" +
                            "<p> </p>")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(yuu_Tanaka)
                    .types(types8)
                    .translator(account)
                    .build());

            //novel 9
            List<String> typesName9 = new ArrayList<>();
            typesName9.add("Slice of Life");
            typesName9.add("Adapted to Anime");
            typesName9.add("Comedy");
            typesName9.add("Drama");
            typesName9.add("Romance");
            List<Type> types9 = typeRepository.findByNameIn(typesName9);
            Author author9 = authorRepository.save(Author.builder().name("凌石更 (Lăng Thạch Canh)").otherName("猫夜叉").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s10793-0e73a258-348b-4177-b9bd-a842c002d817-m.jpg")
                    .name("RE:Yandere")
                    .otherName("从零开始的病娇生活")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Lâm Trạch phát hiện mình có năng lực 'Quay ngược cái chết'! Nhưng điều này có phải là may mắn hay không thì không biết được. Nếu cái chết là hồi kết của sự sợ hãi, vậy khi không thể lựa chọn cái chết thì nỗi sợ càng kinh khủng hơn!</p>\n")
                    .extraNote("Trình độ tiếng Nhật của trans chỉ ở mức \"Arigathanks, Sorrymasen, Chotto minute\" nên truyện được dịch từ bản Eng.\n" +
                            "\n" +
                            "Nguồn Light Novel\n" +
                            "\n" +
                            "Đồng thời xin cảm ơn bạn Blank.428 đã lên màu những tấm hình minh họa vô cùng chất lượng.\n" +
                            "\n" +
                            "Chúc mọi người đọc truyện vui vẻ.")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(author9)
                    .types(types9)
                    .translator(account)
                    .build());

            //novel 10
            List<String> typesName10 = new ArrayList<>();
            typesName10.add("Slice of Life");
            typesName10.add("Adapted to Anime");
            typesName10.add("Comedy");
            typesName10.add("Drama");
            typesName10.add("Romance");
            List<Type> types10 = typeRepository.findByNameIn(typesName10);
            Author author10 = authorRepository.save(Author.builder().name("Kaedehara Kouta - 楓原こうた").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s13178-10005548-b181-434f-a614-732b0d8862eb-m.jpg")
                    .name("Mahou Gakuen no Taizai Majutsushi")
                    .otherName("The Deadly Sins Sorcerer of Magic Academy")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>Đây là câu chuyện về Julis, người không thể dùng được pháp thuật, nhập học tại Học Viện Pháp Thuật cùng Thánh nữ Cecilia, linh mục tối cao nhất, tham gia vào các sự kiện khác nhau của trường học.</p>\n" +
                            "<p>「Pháp thuật rất mạnh?…..Chẳng phải mọi người hơi đề cao nó quá sao?」</p>")
                    .extraNote("Trình độ tiếng Nhật của trans chỉ ở mức \"Arigathanks, Sorrymasen, Chotto minute\" nên truyện được dịch từ bản Eng.\n" +
                            "\n" +
                            "Nguồn Light Novel\n" +
                            "\n" +
                            "Đồng thời xin cảm ơn bạn Blank.428 đã lên màu những tấm hình minh họa vô cùng chất lượng.\n" +
                            "\n" +
                            "Chúc mọi người đọc truyện vui vẻ.")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(author10)
                    .types(types10)
                    .translator(account)
                    .build());
            //novel 11
            List<String> typesName11 = new ArrayList<>();
            typesName11.add("Slice of Life");
            typesName11.add("Adapted to Anime");
            typesName11.add("Comedy");
            typesName11.add("Drama");
            typesName11.add("Romance");
            List<Type> types11 = typeRepository.findByNameIn(typesName11);
            Author author11 = authorRepository.save(Author.builder().name("Wataru Watari").build());
            Artist artist11 = artistRepository.save(Artist.builder().name("Ponkan8").build());
            novels.add(Novel.builder()
                    .avatar("https://i.docln.net/lightnovel/covers/s10793-0e73a258-348b-4177-b9bd-a842c002d817-m.jpg")
                    .name("Yahari Ore no Seishun Rabukome wa Machigatteiru Shin")
                    .otherName("OreGairu Shin")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("<p>OreGairu Shin - Hậu truyện của series Light Novel nổi tiếng OreGairu. Nội dung câu chuyện kể về cuộc sống của Hachiman và những người bạn của mình khi học năm ba tại cao trung Sobu. </p>\n" +
                            "<p>Một năm học mới, học kì mới, những mối quan hệ của Hachiman đang dần được phát triển hơn. Đặc biệt là mối quan hệ tình cảm của Hachiman với Yukino.</p>\n" +
                            "<p> </p>")
                    .extraNote("<p>Nguồn nhật: mình mua truyện bên nhật về để dịch.</p>\n" +
                            "<p>Link bản eng của truyện để tham khảo ( đã full ): <a href=\"https://oregairu.neocities.org/views/shin-novels.html?fbclid=IwAR04ffTc8ykwtqaqi1D10raDfNZshSOBZS_w0OXIxpHqXTQHLB53YjUZ52w\" rel=\"nofollow noreferrer noopener\" target=\"_blank\">https://oregairu.neocities.org/views/shin-novels.html?fbclid=IwAR04ffTc8ykwtqaqi1D10raDfNZshSOBZS_w0OXIxpHqXTQHLB53YjUZ52w</a></p>\n" +
                            "<p> </p>")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(author11)
                    .artist(artist11)
                    .types(types11)
                    .translator(account)
                    .build());

            novelRepository.saveAll(novels);
        }
    }

    private void addTransTeam() {
        if (translationTeamRepository.findAll().isEmpty()) {
            Account account = accountRepository.findByUsername("admin").orElse(null);
            if (account == null) {
                throw new UsernameNotFoundException("Username not Exist!");
            }
            TranslationTeam translationTeam = TranslationTeam.builder().name("No Name Team").description("This is sparta").build();
            List<RoleTeam> roleTeams = roleTeamRepository.findAll();
            translationTeamRepository.save(translationTeam);
            Member member = Member.builder().account(account).translationTeam(translationTeam).roles(new HashSet<>(roleTeams)).build();
            memberRepository.save(member);
        }
    }

    private void addType() {
        if (typeRepository.findAll().isEmpty()) {
            List<Type> types = new ArrayList<>();
            types.add(Type.builder().name("Action").description("Thể loại hành động, thường có nội dung về đánh nhau, bạo lực, hỗn loạn, với diễn biến nhanh.").build());
            types.add(Type.builder().name("Adapted to Manga").description("Truyện đã được chuyển thể thành Manga.").build());
            types.add(Type.builder().name("Adapted to Anime").description("Truyện đã được chuyển thể thành Anime.").build());
            types.add(Type.builder().name("Adapted to Drama CD").description("Truyện đã được chuyển thể thành Drama CD.").build());
            types.add(Type.builder().name("Adult").description("Nội dung nhạy cảm.").build());
            types.add(Type.builder().name("Adventure").description("Thể loại phiêu lưu, mạo hiểm, thường là hành trình của các nhân vật.").build());
            types.add(Type.builder().name("Age Gap").description("Trong truyện có thể hiện tình cảm giữa các nhân vật với cách biệt tuổi tác trên 5 năm hoặc hơn.").build());
            types.add(Type.builder().name("Boys Love").description("Truyện có yếu tố đồng tính nam.").build());
            types.add(Type.builder().name("Character Growth").description("Thể loại tập trung vào sự phát triển của nhân vật theo từng giai đoạn.").build());
            types.add(Type.builder().name("Chinese Novel").description("Light Novel được viết bằng tiếng Trung Quốc, bao gồm các truyện có xuất xứ Đại Lục và Đài Loan").build());
            types.add(Type.builder().name("Comedy").description("Thể loại có các tình tiết gây cười, các xung đột nhẹ nhàng nhưng tạo được tiếng cười nơi độc giả.").build());
            types.add(Type.builder().name("Cooking").description("Những truyện có nội dung liên quan đến việc nấu ăn").build());
            types.add(Type.builder().name("Different Social Status").description("Nội dung thiên về giải quyết sự khác biệt giữa các giai cấp, giai tầng xã hội.").build());
            types.add(Type.builder().name("Drama").description("Thể loại kịch tính, mang đến cho người xem những cảm xúc khác nhau: buồn bã, căng thẳng thậm chí là bi phẫn.").build());
            types.add(Type.builder().name("Ecchi").description("Thể loại có những tình huống nhạy cảm nhằm lôi cuốn người xem.").build());
            types.add(Type.builder().name("English Novel").description("Các truyện được viết bằng tiếng A").build());
            types.add(Type.builder().name("Fantasy").description("Thể loại xuất phát từ trí tưởng tượng phong phú, từ pháp thuật đến thế giới trong mơ thậm chí là những câu chuyện thần tiên.").build());
            types.add(Type.builder().name("Female Protagonist").description("Truyện có nhân vật chính là nữ.").build());
            types.add(Type.builder().name("Game").description("Thể loại có nội dung xoay quanh các trò chơi hay lấy ý tưởng hoặc phỏng theo trò chơi.").build());
            types.add(Type.builder().name("Gender Bender").description("Là một thể loại trong đó giới tính của nhân vật bị lẫn lộn: nam hoá thành nữ, nữ hóa thành nam...").build());
            types.add(Type.builder().name("Harem").description("Thể loại truyện tình cảm, lãng mạn mà trong đó, nhiều nhân vật thích một nhân vật chính").build());
            types.add(Type.builder().name("Historical").description("Thể loại truyện lấy cảm hứng từ các sự kiện lịch sử hoặc cốt truyện gắn liền với các sự kiện lịch sử với dòng thời gian đóng vai trò quan trọng.").build());
            types.add(Type.builder().name("Horror").description("Thể loại rùng rợn, làm cho bạn kinh hãi, khiếp sợ, ghê tởm, run rẩy, có thể gây shock - một thể loại không dành cho người yếu tim.").build());
            types.add(Type.builder().name("Incest").description("Truyện đề cập đến tình yêu giữa các thành viên trong gia đình").build());
            types.add(Type.builder().name("Isekai").description("Mang ý nghĩa \"thế giới khác\". Đối với thể loại này, nhân vật chính thường sống lại (trọng sinh) về quá khứ, chuyển sinh đến thế giới khác, hoặc bị triệu hồi .... Nói tóm lại là đến một thế giới khác.").build());
            types.add(Type.builder().name("Josei").description("Là thể loại được sáng tác chủ yếu bởi phụ nữ cho những độc giả nữ từ 18 đến 30. Josei có thể miêu tả những cảnh lãng mạn thực tế, có cốt truyện rõ ràng, chín chắn trái ngược với hầu hết các kiểu lãng mạn lí tưởng trong truyện Shoujo.").build());
            types.add(Type.builder().name("Korean Novel").description("Là thể loại được sáng tác chủ yếu bởi phụ nữ cho những độc giả nữ từ 18 đến 30. Josei có thể miêu tả những cảnh lãng mạn thực tế, có cốt truyện rõ ràng, chín chắn trái ngược với hầu hết các kiểu lãng mạn lí tưởng trong truyện Shoujo.").build());
            types.add(Type.builder().name("Magic").description("Truyện có các yếu tố liên quan đến ma thuật như phù phép, bùa chú.").build());
            types.add(Type.builder().name("Martial Arts").description("Truyện có liên quan đến võ thuật, bao gồm cả các môn võ đối kháng thực tế lẫn hư cấu (như quyền thuật hay kiếm thuật)").build());
            types.add(Type.builder().name("Mature").description("Truyện có những yếu tố dành cho người trưởng thành (trên 18 tuổi)").build());
            types.add(Type.builder().name("Mecha").description("Thể loại nói tới những robot hoặc máy móc, thường là những cỗ máy biết đi do phi công cầm lái.").build());
            types.add(Type.builder().name("Military").description("Thể loại liên quan đến quân sự, thường đi kèm với chiến tranh hoặc lịch sử.").build());
            types.add(Type.builder().name("Misunderstanding").description("Nội dung xoay quanh sự hiểu nhầm giữa các nhân vật").build());
            types.add(Type.builder().name("Mystery").description("Thể loại bí ẩn, thường xuất hiện những điều bí ấn không thể lí giải được và sau đó là những nỗ lực của nhân vật chính nhằm tìm ra câu trả lời thỏa đáng.").build());
            types.add(Type.builder().name("Netorare").description("Netorare là thể loại mà vợ hoặc người yêu của nhân vật chính bị người khác chiếm đoạt.").build());
            types.add(Type.builder().name("One shot").description("Những truyện ngắn, thường kết thúc trong 1 chương hay 1 tập duy nhất.").build());
            types.add(Type.builder().name("Otome Game").description("Loại truyện mà nhân vật đầu thai vào thế giới game dành cho nữ (còn gọi là Otome Game)").build());
            types.add(Type.builder().name("Psychological").description("Thể loại liên quan đến những vấn đề về tâm lý của nhân vật ( tâm thần bất ổn, điên cuồng ...)").build());
            types.add(Type.builder().name("Reverse Harem").description("Tương tự Harem nhưng nhân vật chính là nữ").build());
            types.add(Type.builder().name("Romance").description("Thể loại đề cập đến chuyện tình cảm lãng mạn, thường là tình yêu giữa hai phái.").build());
            types.add(Type.builder().name("School Life").description("Thể loại học đường, ngữ cảnh diễn biến câu chuyện chủ yếu ở trường học.").build());
            types.add(Type.builder().name("Science Fiction").description("Bao gồm những chuyện khoa học viễn tưởng, đa phần chúng xoay quanh nhiều hiện tượng liên quan tới khoa học, công nghệ, tuy vậy thường thì những câu chuyện đó không gắn bó chặt chẽ với các thành tựu khoa học hiện thời" +
                    ", mà là do con người tưởng tượng ra.").build());
            types.add(Type.builder().name("Seinen").description("Thể loại dành cho lứa tuổi thanh niên (18-30 tuổi) nhưng thường phù hợp cho cả độ tuổi lớn hơn." +
                    " Truyện thuộc thể loại này có thể có những tình tiết bạo lực và khiêu dâm.").build());
            types.add(Type.builder().name("Shoujo").description("Đối tượng hướng tới của thể loại này là phái nữ. Nội dung thường liên quan đến tình cảm lãng mạn.").build());
            types.add(Type.builder().name("Shoujo ai").description("Thể loại quan hệ hoặc liên quan tới đồng tính nữ, thể hiện trong các mối quan hệ trên mức bình thường giữa các nhân vật nữ.").build());
            types.add(Type.builder().name("Shounen").description("Thể loại hương tới đối tượng độc giả là nam thanh niên.").build());
            types.add(Type.builder().name("Shounen ai").description("Thể loại có nội dung về tình yêu giữa những chàng trai trẻ, mang tính chất lãng mạn nhưng ít đề cập đến quan hệ tình dục.").build());
            types.add(Type.builder().name("Slice of Life").description("Thể loại nói về cuộc sống đời thường.").build());
            types.add(Type.builder().name("Slow Life").description("Truyện chủ yếu nói về việc nhân vật tận hưởng cuộc sống nhàn nhã, ít có yếu tố gay cấn.").build());
            types.add(Type.builder().name("Sports").description("Đúng như tên gọi, những môn thể thao như bóng đá, bóng chày, bóng chuyền, đua xe, cầu lông,... là một phần của thể loại này.\n").build());
            types.add(Type.builder().name("Super Power").description("Thể loại mà các nhân vật thường sở hữu siêu năng lực hay các kỹ năng siêu phàm.").build());
            types.add(Type.builder().name("Supernatural").description("Thể loại siêu nhiên sẽ xuất hiện những sức mạnh đáng kinh ngạc và không thể giải thích được, chúng thường đi kèm với những sự kiện trái ngược hoặc thách thức với" +
                    " định luật vật lý.").build());
            types.add(Type.builder().name("Suspense").description("Thường có những tình tiết, diễn biến kịch tính tạo sự hồi hộp cho độc giả.").build());
            types.add(Type.builder().name("Tragedy").description("Thể loại bi kịch, chứa đựng những sự kiện dẫn đến kết cục là những mất mát hay sự rủi ro to lớn.").build());
            types.add(Type.builder().name("Web Novel").description("Những truyện được viết trên blog cá nhân, không chính thức được xuất bản.").build());
            types.add(Type.builder().name("Wars").description("Thể loại truyện có mô tả chi tiết về chiến tranh, chiến trận.").build());
            types.add(Type.builder().name("Workplace").description("Truyện có mô tả chi tiết về các công việc và vấn đề trong công sở.").build());
            types.add(Type.builder().name("Yuri").description("Thể loại về đồng tính nữ, có thể có cảnh sex.").build());

            typeRepository.saveAll(types);
        }
    }

    private void addArtist() {
        if (artistRepository.findAll().isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            artists.add(Artist.builder().name("TEDDY").build());
            artists.add(Artist.builder().name("Chiri").build());
            artists.add(Artist.builder().name("Kantoku").build());
            artistRepository.saveAll(artists);
        }
    }

    private void addAuthor() {
        if (authorRepository.findAll().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            authors.add(Author.builder().name("Yomi Hirasaka").build());
            authors.add(Author.builder().name("Kotobuki Yasukiyo").build());
            authors.add(Author.builder().name("Nezaki Takeru").build());
            authorRepository.saveAll(authors);
        }
    }

    private void addRole() {
        if (roleRepository.findAll().isEmpty()) {
            Role admin = Role.builder().name("admin").build();
            Role mod = Role.builder().name("mod").build();
            Role user = Role.builder().name("user").build();
            List<Role> roles = new ArrayList<>();
            roles.add(admin);
            roles.add(mod);
            roles.add(user);
            roleRepository.saveAll(roles);
        }
    }

    private void addUser() {
        if (accountRepository.findAll().isEmpty()) {
            List<Role> roles = roleRepository.findAll();
            Set<Role> roleSet = new HashSet<>(roles);
            String pass = bCryptPasswordEncoder.encode("admin");
            Account account = Account.builder().name("admin").username("admin").email("admin@gmail.com").hashPass(pass).status(AccountStatusEnum.ACTIVE).roles(roleSet).build();
            accountRepository.save(account);
        }
    }

    private void addTeamRole() {
        if (roleTeamRepository.findAll().isEmpty()) {
            RoleTeam admin = RoleTeam.builder().name("admin").build();
            RoleTeam mod = RoleTeam.builder().name("mod").build();
            RoleTeam member = RoleTeam.builder().name("member").build();
            List<RoleTeam> roles = new ArrayList<>();
            roles.add(admin);
            roles.add(mod);
            roles.add(member);
            roleTeamRepository.saveAll(roles);
        }
    }

}
