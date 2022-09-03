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
            Chapter imotouChap = Chapter.builder()
                    .volume(imotou)
                    .title("Tên tiểu thuyết gia cuồng em gái")
                    .content("<p id=\"1\">“Anh ơi, dậy đi nào, dậy đi, dậyyy…”</p><p id=\"2\">Khi tôi mở mắt để đáp lại giọng nói ấy, thứ chào đón tôi không gì khác ngoài hình ảnh của Alice, đang hoàn toàn khỏa thân.</p><p id=\"3\">Alice là em gái của tôi. Năm nay em ấy vừa tròn 14 tuổi nhưng mái tóc vàng suôn mượt cùng đôi mắt đỏ thẫm màu thạch anh của em ấy lại để lại một ấn tượng khó quên. Em ấy thật xinh đẹp, không ai có thể phủ nhận điều đó.</p><p id=\"4\">“Mngh…, chào buổi sáng, Alice,”</p><p id=\"5\">Tôi ấp úng đáp, vẫn còn đang ngái ngủ. Em ấy chỉ cười khúc khích để đáp lại tôi.</p><p id=\"6\">“Anh hai, sáng nay anh ngủ nướng thật đó! Với một người anh trai ngái ngủ như vậy, đây, hãy nhận lấy…”</p><p id=\"7\">Em ấy nhanh chóng áp mặt lại gần… và trao cho tôi một nụ hôn.</p><p id=\"8\">“…!”</p><p id=\"9\">Đôi môi mềm mại của Alice hạ cánh trên môi của tôi, xua đi mọi cơn buồn ngủ đang đeo bám.</p><p id=\"10\">“Đã tỉnh táo hơn chưa?”</p><p id=\"11\">Em ấy dần tách ra, nở một nụ cười ranh mãnh. Đôi gò má dần ửng hồng.</p><p id=\"12\">“Hôm nay Alice đã nấu cho anh một bữa sáng siêu-cấp-đặc-biệt đấy! Hãy ăn trước khi chúng nguội nào!”</p><p id=\"13\">“Oh! Anh xuống ngay,” tôi đáp.</p><p id=\"14\">Em ấy gật đầu, trông thật thỏa mãn trong ‘bộ đồ bằng da’ và rời khỏi phòng. Mông em ấy, mềm mại và căng tròn như một con tôm vừa mới lột vỏ, chúng lắc lư qua lại nhịp nhàng theo từng bước chân rời khỏi phòng.</p><p id=\"15\">Tôi luôn khởi đầu ngày mới của mình như thế này đã hàng trăm lần, nhưng tôi chưa bao giờ thấy chán cả. Tôi rời khỏi giường trong khi suy ngẫm về những niềm vui thuần khiết này, mong chờ được tận hưởng bữa ăn ngon lành mà em ấy đã chuẩn bị. Kế đó, tôi rửa mặt bằng nước ấm vừa mới ngâm mình của em gái, lau mặt bằng áo ngực vừa mới cởi vẫn còn nóng hổi của em ấy và tiến đến phòng ăn. Thật bất ngờ, Yoshiko, người mà theo tôi nhớ vừa mới chết ngày hôm qua, đang ở đây.</p><p id=\"16\">“Nào, anh hai,” Alice, vẫn đang khỏa thân, nở một nụ cười tan chảy trái tim, “ăn ngay khi còn nóng nào!”</p><p id=\"17\">“Cảm ơn em!”</p><p id=\"18\">Món cơm chiên trứng của em ấy vẫn tuyệt vời như mọi khi. Cả món sữa đi kèm cũng vậy, mùi vị này hoàn toàn bỏ xa những loại sữa mà tôi từng uống. Trứng do chính em ấy đẻ ra, sữa tươi vừa mới vắt của em ấy, một sự kết hợp tối thượng.</p><p id=\"19\">“Nàyy, anh hai, có tương cà dính lên mặt anh kìa! Ooh, meeowww, để xem… Um, có thứ gì để lau không nhỉ, thứ gì đó để lau…”</p><p id=\"20\">Khéo léo mở ra một cổng không gian thông đến chiều không gian khác, Alice lấy ra một chiếc quần lót bông ấm áp và chấm vào một bên khóe miệng tôi. Mùi hương quyến rũ đến từ không gian khác của Alice phảng phất nơi cánh mũi, làm chúng trở hưng phấn và kích thích mong muốn được ăn trong tôi. Ước gì tôi có thể ăn những cái quần lót này. Nhoàm nhoàm… A! Tôi thực sự đã nhai chúng. Nhồm nhoàm, nhồm nhoàm… Chúng mới ngon làm sao.</p><p id=\"21\">Sau đó, tôi tiếp tục bữa sáng với áo ngực của em gái. Alice đáp lại bằng một cái bĩu môi.</p><p id=\"22\">“Nàyy, anh haiii… Nếu anh thật sự muốn nếm thử chiếc quần lót đáng yêu của em như vậy, em sẽ chuẩn bị cho anh một cái mới vẫn còn nóng hổi cùng với sữa tươi nguyên chất của mình nhé! Tee-hee! ©”</p><p id=\"23\">“Thật ư! Anh không thể đợi tới lúc được thưởng thức thứ đồ lót tươi ngon, nóng hổi, vừa mới ra lò của em!”</p><p id=\"24\">-------------------------</p><p id=\"25\">“Cái quái gì đây?!”</p><p id=\"26\">“Hả! Có... có vấn đề gì à?’</p><p id=\"27\">Itsuki lập tức đứng thẳng dậy khi Toki đập bản thảo của cậu xuống bàn và hét vào mặt cậu.</p><p id=\"28\">“Cậu nghĩ cái quái gì vậy?... Cá-cái thế giới quan của cậu thật sự điên rồ… Tôi cảm thấy như bản thân vừa đi vào một cái bãi rác vậy!” Toki vừa trừng mắt nói vừa cố gắng điều chỉnh nhịp thở của mình.</p><p id=\"29\">Itsuki khoanh tay lại, nở một nụ cười như vừa đánh bại cả thế giới.</p><p id=\"30\">“Heh, có vẻ như lại có thêm một tên nữa khuất phục trước cái thế giới tuyệt vời của tôi rồi nhỉ?”</p><p id=\"31\">“Cậu… cái tên điên khùng này…” Toki đáp lại với khuôn mặt căng như dây đàn.</p><p id=\"32\">Itsuki – cụ thể, Itsuki Hashima – một tiểu thuyết gia, 20 tuổi, có chút nhỏ con và lanh lợi hơn so với độ tuổi. Cặp mắt sắc bén của cậu tạo nên một vẻ ngoài trông như một nhân vật phản diện nào đó, song trên khuôn mặt vẫn còn đọng lại những nét ngây thơ đúng độ tuổi – dù cho cậu đang nhìn chằm chằm vào Toki một cách trơ trẽn, rõ ràng là cậu đang muốn gây hấn. Itsuki Hashima là tên thật của cậu; cậu không chọn cho mình một bút danh nào khác giống như cách những nhà văn cùng thể loại với cậu vẫn hay làm.</p><p id=\"33\">Trong khi đó, Kenjiro Toki, biên tập viên của Itsuki và là một người đàn ông trông khá nóng tính, hiện 26 tuổi, mang kính và vận một bộ quần áo công sở. Anh ấy và Itsuki đang họp bàn về kịch bản. Họ thường liên lạc với nhau thông qua tin nhắn và điện thoại, nhưng mỗi khi có thể, Itsuki muốn gặp trực tiếp Toki và để anh đọc trực tiếp bản in cứng[note43457] từ bản thảo của cậu. Cậu nghĩ việc đọc trực tiếp như vậy giúp cậu có thể đánh giá chuẩn chỉ hơn về bản thảo của mình thông qua những phản ứng chân thực của biên tập viên.</p><p id=\"34\">Hôm nay họ đang ở trong căn hộ của Itsuki – một địa điểm gặp mặt quen thuộc, bởi nó chỉ cách nơi làm việc của Toki khoảng chừng 5 phút đi bộ.</p><p id=\"35\">“… Rồi, để chắc chắn rằng chúng ta vẫn đang cùng tần số,” Toki đánh tiếng, giọng của anh trở nên yếu đi bởi sự ngán ngẫm, “đây là bản thảo đề xuất cho chương 2 của ‘Thợ săn quỷ ở xứ Scarlet’?”</p><p id=\"36\">“Đúng vậy.”</p><p id=\"37\">Itsuki gật đầu cái rụp. Hành động này chỉ khiến Toki ngày càng trở nên nhăn nhó.</p><p id=\"38\">“…Vậy, không phải là nó hơi dị à? Bởi vì theo cốt truyện, dường như chương 2 sẽ bắt đầu bằng cảnh anh hùng bất ngờ trước sự xuất hiện của nữ chính tại bữa sáng, dù cô đã hy sinh tính mạng để bảo vệ cậu khỏi đòn tấn công của một tên quỷ ở cuối chương 1…đúng không?”</p><p id=\"39\">“Vâng. Tôi đã bám rất sát vào cốt truyện đó mà. Tôi nghĩ nó thật sự hoàn hảo đấy.”</p><p id=\"40\">Toki đang đề cập đến một sơ đồ viết tay về cốt truyện mà cả hai người đã cùng nhau nghĩ ra trước đây.</p><p id=\"41\">“Theo sát cốt truyện này á?”</p><p id=\"42\">Itsuki hơi cau mày lại khi Toki đập tay xuống bàn.</p><p id=\"43\">“Gì chứ? Anh hùng đã tỏ ra bất ngờ mà, đúng không? Cô gái đã ngã xuống trước mặt anh ấy ngày hôm qua, và ngay bậy giờ lại xuất hiện ở đây… Um, cô ấy tên gì ấy nhỉ?”</p><p id=\"44\">“Sao cậu lại có thể quên được tên của nữ chính chứ! Cô ấy là Yoshiko, nghe chưa? Yoshiko! Cậu không nghĩ đó là một cái tên xinh xắn à? Đó thật sự là một cái tên tuyệt vời dành cho một cô gái luôn chiến đấu đơn độc, thầm lặng, ẩn mình trong bóng tối của vương quốc để chống lại lũ quỷ! …Được rồi, ý tôi ở đây là cậu đã cố gắng nhồi nhét cô ấy vào đoạn này để làm anh hùng thấy sốc. Là do tôi đã hoàn toàn bỏ qua đoạn đó, nhưng mà…”</p><p id=\"45\">“Này, anh biên tập à”</p><p id=\"46\">Itsuki cố tình thở ra một hơi</p><p id=\"47\">“Anh không thấy cay cú khi anh đã viết một thứ rõ như ban ngày trong câu chuyện của mình rồi, nhưng người đọc lại quên mất hay thậm chí là bỏ qua chúng, sau đó họ lại quay ra chỉ trích anh kiểu như ‘tại sao chi tiết này lại không được đề cập?’ hay ‘cái kiểu truyện gì thế này?’ à?</p><p id=\"48\">“Đừng có nói cái kiểu như tôi là người xấu như vậy!” Toki lên giọng đáp trả, rồi lại hít một hơi thật sâu để trấn tĩnh bản thân. “Việc nhồi nhét Yoshiko ở đoạn này là một chuyện, nhưng vấn đề chính tôi muốn nói ở đây không phải điều đó.”</p><p id=\"49\">“Ý anh là gì?”</p><p id=\"50\">Toki gõ gõ ngón giữa vào bản in trước mặt, “Vấn đề chính ở đây, nhân vật nữ mới xuất hiện tên Alice này là ai vậy? Tôi không nhớ là mình đã được nghe thông tin nào về cô ta cả!”</p><p id=\"51\">“Cô ấy là em gái của anh hùng. Tôi nhớ là đã viết nó ở phần mô tả nhân vật của anh hùng rồi mà? Anh hùng có một đứa em gái.”</p><p id=\"52\">“Đúng là cậu đã viết như vậy! Nhưng cậu lại chẳng thêm bất cứ thông tin chi tiết nào về nhân vật này cả, tôi nghĩ cô ta chỉ là một nhân vật thứ cấp. Không một lời giải thích… và giờ cậu cho tôi xem cái thể loại kinh khủng này…!”</p><p id=\"53\">“Nó đẹp kinh khủng luôn đúng không? Tôi cũng nghĩ vậy đấy!”</p><p id=\"54\">“Không phải như vậy, thằng ngu này! Tuy đó là sở thích của cậu… Ý tôi là, việc cậu để chế độ mặc định của cô ta là khỏa thân đã đủ quái dị rồi, nhưng còn những chuyện điên khùng hơn thế nữa xảy ra, bây giờ tôi chả thể hiểu nổi cái đống lộn xộn này là gì cả! Anh hùng đi ngửi mùi áo ngực? Anh hùng đi ăn một cái quần lót theo đúng nghĩa đen?... Cậu viết ra cái thể loại anh hùng củ chuối gì thế hả? Cậu có chắc là mình không viết nhầm ‘bánh kếp’ thành ‘quần lót’ không đấy? Hay gì đó đại loại thế…”</p><p id=\"55\">“Này, đừng hỏi ngớ ngấn thế chứ. Anh nghĩ là tôi sẽ phạm phải một cái sai làm nghiệp dư vậy à?”</p><p id=\"56\">“Được rồi, cho là cậu không nhầm đi… Thế cái được gọi là ‘sữa’ của Alice ở đây là cái quái gì vậy?”</p><p id=\"57\">“Thì đúng như tôi viết đấy thôi. Đó là sữa lấy từ ngực của Alice. Cực kỳ phì nhiêu.”</p><p id=\"58\">“Vậy còn mấy quả trứng…”</p><p id=\"59\">“Ừm, cô ấy đẻ ra chúng. Hm, kiểu như, chúng bổ dưỡng gấp nghìn lần so với trứng cá muối ấy, à, không phải là tôi thích trứng cá muối đâu nhé.”</p><p id=\"60\">“Ha-ha-ha… Tất cả mấy thứ này thật quá mức điên khùng! Còn cậu, chủ nhân của cái mớ kiệt tác VL chim én này, mới thật sự là thứ có vấn đề nhất đấy! Tôi cứ nghĩ anh hùng chỉ là một học sinh trung học bình thường xuất thân từ một gia đình kiểu mẫu thôi đấy! Cái màn thể hiện quá mức củ chuối này sẽ đánh bay thứ được gọi là ‘Dòng máu của thợ săn quỷ’ cùng mấy thứ khác khỏi đầu độc giả đấy!”</p><p id=\"61\">Một tràng liên thanh như vậy bắn tới khiến cho Itsuki khẽ cau mày.</p><p id=\"62\">“Hmm… Được rồi, nếu anh đã nói vậy thì có lẽ mấy chi tiết liên quan đến sữa-và- trứng đã đi hơi xa một chút rồi… Mà anh biết đấy, tôi đã phải hình dung rất nhiều cảnh chiến đấu siêu nhiên rồi, tôi chỉ muốn trốn tránh thực tại một tý với những ảo tưởng của tuổi trẻ thôi mà…”</p><p id=\"63\">“Một chút thôi á…? Cậu nghiêm túc…?” Toki đã giận đến mức run run người.</p><p id=\"64\">“Anh có thể bắt gặp những thứ này trong mấy cuốn sách giống vậy mà. Kiểu như, cha mẹ của anh hùng là những mạo hiểm giả nổi tiếng, hay họ là truyền nhân của một môn phái cổ truyền nào đó và anh hùng được thừa hưởng những tinh hoa từ họ.”</p><p id=\"65\">“Rồi, bây giờ cậu muốn nói rằng cái nhân vật điên khùng có thể đẻ trứng, và cả cái việc nhai nhồm nhoàm quần lót này đều liên quan đến cốt truyện cơ bản của một bộ truyện tranh anh hùng?</p><p id=\"66\">Có thể trông thấy khói đang bốc ra từ đầu của Toki lúc này.</p><p id=\"67\">“… Vâng,” Itsuki rón rén lên tiếng, “Nếu anh có một đứa em gái, và nếu em ấy ngâm bồn từ trước, thì hiển nhiên là phải rửa mặt bằng nước ngâm còn ấm em ấy vừa mới sử dụng còn gì?”</p><p id=\"68\">“Cậu gọi điều đó là hiển nhiên á?! Tên cuồng em gái chết tiệt!”</p><p id=\"69\">Toki hét lên bằng tất cả sức lực của mình.</p><p id=\"70\">***</p><p id=\"71\">Itsuki Hashima ra mắt lần đầu vào năm 16 tuổi sau khi giành được giải thưởng dành cho những tác giả trẻ. Trong những năm tiếp theo, cậu đã xuất bản tổng cộng 20 cuốn tiểu thuyết khác nhau, trong đó bao gồm 5 bộ truyện ngắn một tập và 3 bộ truyện dài kỳ.</p><p id=\"72\">Mặc dù cậu đã bắt đầu viết tiểu thuyết từ trước khi trở thành dân chuyên, nhưng con số 20 cuốn chỉ trong thời gian 3 năm thật sự ấn tượng – và cả chất lượng tiểu thuyết cậu viết ra vẫn luôn được đảm bảo, không có gì lạ khi cậu đã gầy dựng một lượng độc giả hâm mộ riêng cho mình. Thậm chí có vài tựa sách của cậu đã lọt vào top 10 sách bán chạy nhất được phát hành bởi Oricon, một thương hiệu nổi tiếng ở Nhật Bản. Không ngoa khi nói rằng cậu thật sự đang trong đà thăng tiến rực rỡ của sự nghiệp.</p><p id=\"73\">Khi nói đến tốc độ viết lách, trí tưởng tượng, khả năng xây dựng một câu chuyện logic hay những nhân vật hấp dẫn, Itsuki Hashima đã có cho mình mọi thứ để có thể trưởng thành và phát triển trên con đường của một tiểu thuyết gia nổi tiếng – dù có là vậy đi nữa, tất cả cuốn sách cậu đã bán đều ở mức độ “Eh~” nhất định.</p><p id=\"74\">Mọi câu chuyện của cậu đều theo một mô tuýp đã được đóng khung, nữ chính luôn luôn là nhân vật mang tên “em gái”.</p><p id=\"75\">Đây là một kiểu ý tưởng quen thuộc ở dòng tiểu thuyết này, và nó cũng có thể thật sự trở nên nổi tiếng, nhưng cái cách Itsuki cố chấp hình mẫu của nhân vật nữ chính chắc chắn đã khiến cho vài đọc giả phải tròn mắt mà thốt lên, “Lại nữa à…” Không dừng lại ở đó, Itsuki luôn nỗ lực phát triển hình mẫu em gái của riêng mình để không bị trùng lặp với những cuốn tiểu thuyết khác, khiến cho nhân vật này ngày càng hành xử táo bạo hơn qua từng tập truyện. Và đến khi nam chính bắt đầu nói về em gái mình theo cái cách không thể nào điên khùng hơn nữa, việc độc giả dần quay lưng lại với cậu chỉ là vấn đề thời gian.</p><p id=\"76\">Nhận ra việc Itsuki cần có sự thay đổi trong phong cách để có thể phát triển bản thân, Toki đã buộc cậu sáng tác ‘Thợ săn quỷ ở xứ Scarlet’. Và rồi giờ anh phát hiện ra rằng, Itsuki lại vừa tạo ra thêm một con quái vật gắn mác em gái mới.</p><p id=\"77\">“Ugh… Cái thằng ngu phát rồ vì em gái này…”</p><p id=\"78\">Toki đang trên đường trở về văn phòng từ nhà của Itsuki. Anh thở dài một hơi, phả ra làn khói trắng có thể trông thấy được vào tiết trời trong lành của tháng một.</p><p id=\"79\">“A! Chào anh, Toki!”</p><p id=\"80\">Người vừa chào đón anh là một thiếu niên nhỏ nhắn khoác trên mình áo gió cùng chiếc túi từ cừa hàng tiện lợi đang xách ở một bên tay.</p><p id=\"81\">“Oh, chào em, Chihiro,” Toki đáp lời. “Anh vừa mới thảo luận ý tưởng với anh trai của em xong và đang trên đường về.”</p><p id=\"82\">“Vậy à? Cảm ơn anh đã luôn chăm sóc anh trai của em.”</p><p id=\"83\">“Không có gì. Cậu ấy cũng giúp anh nhiều mà.”</p><p id=\"84\">Thiếu niên ấy là Chihiro Hashima, em trai của Itsuki. Cậu đang học năm nhất cao trung, mái tóc dài ngang cổ, làn da trắng trẻo cùng khuôn mặt mang vẻ có chút thờ ơ tạo cho cậu một nét quyến rũ riêng biệt. Itsuki nói rằng cậu là một đứa trẻ hoàn hảo – đứng đầu lớp học, giỏi thể thao, và cả những lĩnh vực khác nữa. Từ nhà Hashima đến căn hộ mà Itsuki đang sống cần tốn khoảng 20 phút lắc lư trên xe buýt, Chihiro thường xuyên ghé qua để phụ giúp việc nấu ăn, dọn dẹp phòng ốc… Điều này đã giúp cậu quen biết Toki.</p><p id=\"85\">“Em lại đến nấu bữa tối hả?”</p><p id=\"86\">“Vâng ạ.”</p><p id=\"87\">“…Hầy, cậu ta thật may mắn khi có em là em trai. Anh thật sự ghen tỵ đấy.”</p><p id=\"88\">“A, không, không hẳn…” Chihiro thoáng xấu hổ bởi những lời thật lòng của Toki. “Bây giờ có lẽ em phải đi rồi. Anh có yêu cầu gì với anh hai thì có thể nói với em nhé.”</p><p id=\"89\">Sau khi cúi chào lịch sự, Chihiro rời đi. Toki vừa nhìn theo bóng lưng cậu vừa thầm nghĩ cậu thật sự là một đứa trẻ tốt bụng và lịch sự. Cậu nấu ăn ngon, luôn quan tâm chăm sóc cho anh trai của mình. Thật hoàn hảo.</p><p id=\"90\">“…Ước gì Chihiro là con gái nhỉ. Nếu vậy thì Itsuki đã không phát điên vì cái sở thích cuồng em gái đến thế… À, vậy thì cậu ta sẽ không trở thành một tiểu thuyết gia như bây giờ mất… Cuộc sống mà…”</p><p id=\"91\"><img src=\"https://i.docln.net/lightnovel/illusts/u114052-e0c5f098-317b-41d7-b260-3aa6454479c2.jpg\" alt=\"u114052-e0c5f098-317b-41d7-b260-3aa6454479c2.jpg\"></p><p id=\"92\">***</p><p id=\"93\">Vài phút sau khi Toki rời đi, Itsuki lần nữa nghe thấy tiếng chuông. Cậu mẫm chắc đó là Chihiro và tiến ra cừa.</p><p id=\"94\">“…Chào em.”</p><p id=\"95\">“Chào.”</p><p id=\"96\">“…Mm.”</p><p id=\"97\">Sau vài câu chào lí nhí, Itsuki để Chihiro vào nhà. Mặc dù họ có thể là người nhà, nhưng vẫn có chút khác thường ở đây, sự khó xử trong cách họ tiếp xúc với nhau. Họ trở thành anh em kế của nhau vào ba năm trước, khi bố của Itsuki tái hôn với mẹ của Chihiro – ngay khi Itsuki vừa ra mắt dưới danh nghĩa tiểu thuyết gia chuyên nghiệp. Đứa con trai lớn đang học năm hai cao trung, đứa nhỏ thì học năm nhất sơ trung – một khoảng thời gian để thay đổi cảm xúc cho cả hai. Việc bất ngờ trở thành anh em khiến hai người trở nên lúng túng, không biết phải cư xử với nhau như thế nào cho phải phép, ngay thời gian đầu họ chỉ hành xử như những người bạn chung phòng đang cố gắng sống cùng nhau.</p><p id=\"98\">Mọi chuyện chỉ thay đổi khi Itsuki học lên đại học và dọn ra ở riêng. Cậu có thể tiếp tục sống tại nhà cùng những người khác mà không gặp khó khăn trong việc di chuyển, nhưng cậu vẫn quyết định thuê căn hộ này, bằng tiền của chính mình và bảo rằng việc này giúp cậu tiết kiệm thời gian để tập trung vào công việc viết lách của bản thân. Sau đó cậu đã thôi học đại học ngay năm nhất và tiếp tục sống ở đây bởi nó khá gần nơi biên tập viên của cậu làm việc.</p><p id=\"99\">Trong khoảng thời gian Itsuki bỏ học, Chihiro thi thoảng vẫn ghé qua để đưa gạo cùng các loại thực phẩm thiết yếu. Bây giờ thì nó đã trở thành thông lệ, em trai của Itsuki sẽ chăm lo việc nấu ăn cũng như các loại việc nhà khác. Việc trở thành tiểu thuyết gia toàn thời gian giúp tiến độ hoàn thành của Itsuki ngày một nhanh hơn, nhưng bù vào đó là kỹ năng sống vốn nghèo nàn của cậu cứ thế mà tệ đi. Đã có thời điểm mà việc ăn ngủ của cậu trở nên vô cùng tệ hại, căn hộ mà cậu sống thì trở thành một đống hỗ lốn chính hiệu. Kể từ đó, Chihiro không thể đứng yên nhìn cái cách sống bừa bãi của cậu được nữa.</p><p id=\"100\">“Đợi tý, em sẽ nấu xong nhanh thôi.”</p><p id=\"101\">“…Ừm.”</p><p id=\"102\">Bây giờ, Chihiro đã mặc tạp dề lên người, xử lý nguyên liệu mà mình mang đến và tiến hành nấu ăn trong căn bếp đã quen thuộc. Itsuki thi thoảng lại liếc nhìn cậu trong khi vẫn thao tác trên laptop, tiếp tục tiểu thuyết của mình. Ba mươi phút sau, họ đã ngồi đối diện nhau trên bàn.</p><p id=\"103\">“Cảm ơn vì bữa ăn.”</p><p id=\"104\">“Không có gì.”</p><p id=\"105\">Chihiro đã làm món tôm với sốt cay, vài món xào kiểu Trung và cả cơm chiên – tất cả đều là tự làm và không món nào Itsuki có thể làm được cả, chúng tỏa ra một hương vị ngon lành, khiến đôi đũa của Itsuki không ngừng bận rộn. Chihiro nở một nụ cười nhẹ khi trông cách mà anh trai cậu thưởng thức các món ăn, không quên nhắc nhở anh mình việc ăn uống sao cho thích hợp. Dù đang lúc ăn cơm đi nữa, cậu vẫn thể hiện được sự mẫu mực của mình. Tinh tế, lôi cuốn, thành tích đứng đầu lớp, thể chất tốt, nấu ăn ngon, biết làm việc nhà, tính cách dễ chịu, hành vi chuẩn mực – thật sự là một người em trai hoàn hảo. Là một người anh trai – hay thậm chí là một thằng đàn ông – Itsuki không thể không cảm thấy có chút cảm giác tự ti. Nó làm cậu muốn tìm hiểu rõ hơn về Chihiro.</p><p id=\"106\">“…Này, ưm, Chihiro? Anh thật sự quý việc em thường xuyên đến đây, nhưng mà, ừm, em không có gì quan trọng hơn để làm sao? Kiểu như… đi hẹn hò với bạn gái của em chẳng hạn?”</p><p id=\"107\">Chihiro có chút bất mãn, “Em không có bạn gái.”</p><p id=\"108\">“Không có?”</p><p id=\"109\">“Không.”</p><p id=\"110\">Đây chắc chắn là vì cậu chưa tìm được người thích hợp với bản thân. Không đời nào Chihiro, trong số tất cả mọi người, lại không thu hút được sự chú ý của người khác giới.</p><p id=\"111\">“Sao em không thử tìm một người đi?”</p><p id=\"112\">“… Em không biết nữa. Em nghĩ là em không thật sự muốn,” cậu nói tiếp. “Hơn nữa, anh biết đấy… Em khá quan ngại về anh đấy.”</p><p id=\"113\">“Aw, hầy, Chihiro, em không cần phải lo lắng cho anh đâu!”</p><p id=\"114\">Chihiro khẽ thở dài.</p><p id=\"115\">“… Được rồi, em sẽ làm vậy nếu anh chịu hợp tác một chút đó?”</p><p id=\"116\">“Này, anh làm được đấy! Chỉ cần muốn là anh có thể đấy.”</p><p id=\"117\">“Thật ư? Vậy anh có thể tự nấu ăn ngày ba bữa? Tất nhiên là những món ăn thật sự, có rau và món ăn kèm, không phải mấy loại mì ăn liền? Anh cũng sẽ cọ rửa nhà tắm cũng như xử lý rác đúng nơi chứ? Anh sẽ cất mấy cái đĩa game khiêu dâm của anh lên kệ chứ?”</p><p id=\"118\">“T-tất nhiên…”</p><p id=\"119\">“Không, anh không thể,” Chihiro trả lời ngay lập tức.</p><p id=\"120\">“Nhìn này, anh hai, anh vừa giặt cái áo len của mình chung với những thứ đồ bình thường khác đúng không? Anh lúc nào cũng thế. Tống mọi thứ cần giặt vào máy, đổ bậy đổ bạ lượng bột giặt mà anh cho là đúng, thiết lập đại một chế độ giặt nào đó rồi nhấn nút.”</p><p id=\"121\">Itsuki cau mày trước những lời chỉ trích bản thân không sai vào đâu được.</p><p id=\"122\">“Có việc thiết lập máy giặt nữa à…? Um… Nh-nhưng có một việc em sai rồi, Chihiro!”</p><p id=\"123\">“Em sai ư?”</p><p id=\"124\">“Đúng vậy, anh không hề cho bột giặt bậy bạ vào đó! Chúng hết rồi và anh cũng không hề đi mua thêm!”</p><p id=\"125\">“Ồooo, thành tích đáng để tự hào đấy. Anh không biết là còn một chai nữa ở phía dưới bồn rửa tay à?”</p><p id=\"126\">“…Ồ, có à?”</p><p id=\"127\">“Ugh…” Lại một tiếng thở dài.</p><p id=\"128\">“Anh không nghĩ mình sẽ thật sự toang nếu không có em à?</p><p id=\"129\">Chihiro bình phẩm, nom cực-kỳ-hài-lòng.</p><p id=\"130\"><img src=\"https://i.docln.net/lightnovel/illusts/u114052-cf8b2c69-8264-4c49-8c90-c3c74f4db3df.jpg\" alt=\"u114052-cf8b2c69-8264-4c49-8c90-c3c74f4db3df.jpg\"></p>\n")
                    .build();
            Chapter arafooChap = Chapter.builder()
                    .volume(arafoo)
                    .title("Ông chú tử vong")
                    .content("<p id=\"1\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-9e644480-a006-4a6f-a20a-94b586e6e61c.jpg\" alt=\"u17587-9e644480-a006-4a6f-a20a-94b586e6e61c.jpg\"></p><p id=\"2\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-6ae7ca8c-bf36-4c86-8a6c-33e0b9623e16.jpg\" alt=\"u17587-6ae7ca8c-bf36-4c86-8a6c-33e0b9623e16.jpg\"></p><p id=\"3\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-c802b985-8254-4ed2-b0ac-5a119944bae3.jpg\" alt=\"u17587-c802b985-8254-4ed2-b0ac-5a119944bae3.jpg\"></p><p id=\"4\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-7b15fb73-1c9e-4624-974c-dce4cee08506.jpg\" alt=\"u17587-7b15fb73-1c9e-4624-974c-dce4cee08506.jpg\"></p><p id=\"5\">Game nhập vai VR-RPG Sword and Sorcery VII.</p><p id=\"6\">Vẫn luôn là Game nhập vai Full-Drive hàng đầu kể từ được phát hành riêng cho hệ thống máy chơi Game đời mới nhất Dream Work.</p><p id=\"7\">Hiện đã được update đến phiên bản thứ 7 kéo theo lượng người chơi trung thành cuồng nhiệt vẫn đang gia tăng không ngừng.</p><p id=\"8\">Hệ thống đồng bộ hóa giác quan được xây dựng thông qua việc kết nối các khớp thần kinh với các cổng kết nối điện tử, quá vượt trội so với bất kỳ hệ thống chơi game nào của các công ty khác. Thế giới ảo được trải nghiệm bằng cả năm giác quan, thu hút sự chú ý của các game thủ tìm cảm giác mạnh và khiến họ nghiện nặng.</p><p id=\"9\">Cho dù giá bán máy rất đắt, nhưng đa số người chơi theo đuổi cái cảm giác kích thích đầy chất sử thi vẫn đắm chìm trong thế giới rộng lớn ngập tràn những cuộc phiêu lưu mạo hiểm ấy.</p><p id=\"10\">Satoshi Osako là một game thủ như vậy, dùng toàn bộ thời gian để tận hưởng thế giới ảo tuyệt vời đó bằng nhân vật Zeros Merlin của mình.</p><p id=\"11\">Nhân vật trong Game của anh ta để tóc mái mọc bừa bãi che khuất cả mắt, râu ria lởm chởm luộm thuộm cùng một khuôn mặt không có gì đặc biệt, cực kỳ giống một ông chú trung niên đã không còn tương lai. Một ông chú trung niên mặc trang bị cao cấp nhất đã được thiết kế tỉ mỉ để có bề ngoài trông y như trang bị cơ bản.</p><p id=\"12\">Đại đa số người chơi khác đều không bao giờ cho rằng ông chú trung niên tầm thường trong chiếc áo choàng xám bẩn thỉu đầy khả nghi ấy lại là một trong những game thủ top 5 của trò chơi.</p><p id=\"13\">Một trong những \"Destroyer\" đứng trên đỉnh cao của thế giới.</p><p id=\"14\">Chế tạo trang bị, vũ khí hay Items phụ trợ vốn là chuyện đương nhiên sẽ có trong mọi trò chơi, điểm hấp dẫn nhất trong Sword and Sorcery VII chính là khả năng sáng tạo ma thuật.</p><p id=\"15\">Trong thế giới ảo này, phép thuật hoạt động bằng cách sắp xếp 56 ký tự cơ bản và 10 chữ số Arab theo thứ tự nhất định, sinh ra đủ loại hiệu quả ma thuật khác nhau.</p><p id=\"16\">Hệ thống Spell Circuits - tên gọi chung cho mọi ma thuật có thể khắc ghi vào trong tiềm thức, cho phép người chơi tùy chỉnh sức mạnh và hiệu ứng của phép thuật một cách thủ công, dẫn đến tình huống tăng giảm lượng Mana tiêu thụ và cả uy lực của câu thần chú. Vấn đề kỳ quặc ở chỗ ma thuật càng tinh xảo, càng phức tạp, uy lực lại càng tăng trong khi lượng Mana tiêu hao lại càng giảm.</p><p id=\"17\">Cho dù đã cố gắng tính toán và điều chỉnh sao cho sát thương gây ra sẽ bằng 0 theo lý thuyết, phép thuật được triển khai vẫn cứ liên tiếp phát huy ra uy lực kinh khủng, dẫn đến việc người chơi đâm đầu vào tìm hiểu các sửa đổi có sẵn cho mỗi phép thuật. Hệ thống quá phức tạp gây ra hỗn loạn, thậm chí khiến trò chơi bị dán nhãn “game rác” trong khoảng thời gian đầu.</p><p id=\"18\">Sau vô số giờ liều mình cày cuốc, cuối cùng người chơi cũng phát hiện ra bí mật được che giấu, rằng nếu sử dụng Mana của nhân vật ảo làm môi giới để vận dụng Mana tự nhiên mới có thể khiến uy lực của phép thuật được triển khai tăng lên. Chỉ cần phù hợp điều kiện, ma pháp thuật thức có thể triển khai, không cần biết là thô thiển vụng về đến mức nào, ma thuật đều có thể hoàn thành. </p><p id=\"19\">Tuy nhiên, vì lượng Mana trong tự nhiên không được thể hiện ra bằng bất cứ tham số nào để tham khảo, cho nên để biết chính xác lượng Mana cần tiêu hao làm môi giới mỗi khi triển khai phép thuật thì trước mắt vẫn đang trong giai đoạn nghiên cứu, hiện tại chỉ có một cách: đoán.</p><p id=\"20\">Spell Circuits gây hỗn loạn khi mới ra mắt, bởi không có gợi ý hay hướng dẫn gì về việc phép thuật có thể bị sửa đổi, việc phát hiện ra hệ thống Spell Circuits hoàn toàn là chuyện của tình cờ.</p><p id=\"21\">Chỉ khi người chơi để tâm lưu ý và kiểm tra thật kỹ mới có thể phát hiện các manh mối chỉ dẫn khi khám phá thế giới trong trò chơi hay các Dungeon, bỏ qua hay nắm bắt gợi ý, tất cả đều tùy vào người chơi.</p><p id=\"22\">Mặc dù trên phương diện là một trò chơi, nó mang đến sự tự do to lớn đến kinh người, nhưng chỉ những game thủ hard core thật sự chìm đắm trong thế giới Game mới có thể tận dụng được hệ thống Spell Circuits, những người chơi thông thường chỉ sử dụng phép thuật mặc định đã được tiêu chuẩn hóa.</p><p id=\"23\">Thí nghiệm để tạo ra phép thuật mới tốn quá nhiều thời gian, cực kỳ phiền toái, thậm chí bị coi là một điểm trừ, đại đa số người chơi bình thường sẽ đi theo hướng tự do hưởng thụ mạo hiểm, khám phá thế giới.</p><p id=\"24\">Tuy nhiên, tùy thuộc vào khả năng của người chơi, có thể tạo ra cả những phép thuật không có cast time hoặc cooldown time, rất nhiều người chơi bất mãn vì sự chênh lệch kinh khủng đó.</p><p id=\"25\">Tuy nhiên, là một game thủ gạo cội hết lòng tận tụy với game, Satoshi hoàn toàn không thèm quan tâm đến ý kiến của người khác.</p><p id=\"26\">Toàn bộ thành viên trong Party đều cho rằng hưởng thụ trò chơi như thế nào là tự do cá nhân của mỗi người. Bọn họ không chế tác những ma thuật mà bản thân sáng tạo ra thành Magic Scroll để bán, vì vậy mà thường xuyên bị chỉ trích vì giấu giếm cho riêng mình những ma thuật cao cấp mạnh mẽ. Dù vậy, không thèm quan tâm tới bất kỳ lời nào trong vô số những chỉ trích đầy tính hãm hại đó, bằng lòng nhiệt tình vượt xa người thường, bọn họ vứt bỏ mọi thường thức lẫn ánh mắt từ người khác dễ như trở bàn tay, vẫn lao đầu vào nghiên cứu sáng tạo ra đủ loại ma thuật. </p><p id=\"27\">Đã trải qua bảy năm kể từ khi trò chơi được phát hành, Party của Satoshi vẫn luôn luôn độc chiếm vị trí tối cao, có thể gọi là những con nghiện Game hạng siêu nặng.</p><p id=\"28\">Ma thuật mà họ sáng tạo ra, phức tạp đến bất thường, những người chơi theo hướng đào sâu khám phá hệ thống khác cũng cảm thấy nan giải thực sự khi phải đối mặt với hệ thống ma thuật mà Party của Satoshi đã sáng tạo ra, cho dù mọi kiến thức cần thiết để sáng tạo ma thuật đều có thể dễ dàng có được từ bất cứ thành phố nào.</p><p id=\"29\">“Đừng có ỷ lại vào nỗ lực của người khác!” Đó là câu trả lời cho mọi lời thắc mắc.</p><p id=\"30\">Ông chú trung niên Satoshi Osako, ngày xưa đã từng nổi tiếng vì là kỹ thuật viên lập trình cho một tập đoàn công nghệ hàng đầu, hiện tại đã nghỉ việc và về sống cô đơn nơi nông thôn.</p><p id=\"31\">Kẻ bị rơi rớt khỏi guồng quay xã hội, ngoại trừ làm việc ngoài đồng ruộng ra thì chỉ có đắm chìm trong game cả ngày, cũng có thể gọi là một dạng Hikikomori.</p><p id=\"32\">Vai trò một Đại Hiền Giả nổi tiếng với địa vị và sức ảnh hưởng to lớn trong thế giới ảo khiến ông chú càng chìm đắm vào sâu hơn vào trong nó.</p><p id=\"33\">Ông chú trung niên, cử nhân thất nghiệp đã hơn 40 tuổi, không gia đình, cũng chẳng còn người thân nào ngoài một bà chị, nơi duy nhất ông chú được yên ổn là chính mình chỉ có thế giới ảo này.</p><p id=\"34\">Ngoại hình sẽ dễ nhìn nếu chịu chăm chút cho bản thân, thậm chí có thể được hoan nghênh chào đón, nhưng cái lối sinh hoạt bừa bãi luộm thuộm đó đã khiến ông chú bỏ lỡ tuổi kết hôn mất rồi.</p><p id=\"35\">Với quá khứ đó, ông chú vận dụng một phần kỹ thuật lập trình vào việc sáng tạo ra những phép thuật hùng mạnh, những thành viên khác trong Party cũng nắm giữ những kỹ thuật tương tự, không nghi ngờ gì khi nói rằng họ đã nhờ vậy mà cùng nhau tạo ra hàng loạt ma thuật ngày càng khủng khiếp hơn nữa. Trong thế giới ảo này, có thể gọi thành viên trong Party của Satoshi là những nhà nghiên cứu ngạo mạn điên cuồng mà lại ngốc nghếch.</p><p id=\"36\">Với tâm thái phải chơi sao cho thật vui vẻ, bọn họ không ngừng sáng chế ra ma thuật uy lực mạnh mà lại tiêu hao ít Mana, càn quét vô số Quest khó cao cấp. Hiện tại, ông chú cùng đồng đội đang chiến đấu với Boss cuối trong cốt truyện - tà thần Evil God.</p><p id=\"37\"><img src=\"https://i.docln.net/lightnovel/illusts/u17587-960a4357-f7c8-4023-b10f-f2a171df51af.jpg\" alt=\"u17587-960a4357-f7c8-4023-b10f-f2a171df51af.jpg\"></p><p id=\"38\">Không biết họ đã chiến đấu trong bao lâu, chỉ biết rằng họ đã đi xa, rất xa, đã sắp đến lúc hạ được Boss cuối này.</p><p id=\"39\">Evil God bị ép phải chuyển hóa sang dạng thứ ba, vốn tràn ngập khí thế đầy tăm tối của sự tà ác, bây giờ đã trở nên thê thảm tàn tạ dưới bàn tay của năm người.</p><p id=\"40\">Cả năm người trong party đều là Pháp Sư, lại trang bị bằng hàng loạt vũ khí ma thuật cải tạo, áp đảo Evil God một cách tàn bạo ngay từ đầu bằng hỏa lực mãnh liệt.</p><p id=\"41\">“Dai quá đi mất, sắp chết chưa ấy nhỉ?”</p><p id=\"42\">“Đây là Boss cuối cơ mà, không chết dễ thế đâu!”</p><p id=\"43\">“A ha… nó chuẩn bị tấn công kìa! Có cần triển khai ma thuật phòng ngự không?” </p><p id=\"44\">Ma thuật công kích hung tàn của Evil God ào tới như muốn xé nát cả mặt đất.</p><p id=\"45\">Bọn họ triển khai nhiều tầng vách chắn ma thuật, chống đỡ đòn tấn công, tiếp theo chớp thời cơ sơ hở xuất hiện trong nháy mắt, vung vũ khí chém tới.</p><p id=\"46\">Cánh tay tà thần bị chặt phăng, rơi xuống cùng với tiếng vang lớn.</p><p id=\"47\">Rõ ràng tất cả đều là pháp sư mà lại làm được như vậy có một phần rất lớn nhờ vào trang bị và ma thuật mà họ cùng nhau sáng tạo ra. Không tiếc tất cả, đầu tư chế tạo ra Items mạnh nhất theo sở thích của mỗi người, thoải mái dùng quái vật làm mục tiêu mà không ngừng thí nghiệm.</p><p id=\"48\">Bọn họ đã khiêu chiến Evil God vô số lần, vẫn luôn thảm bại, lần khiêu chiến này là vì rửa nhục.</p><p id=\"49\">“Kết thúc thôi chứ nhỉ? Lát nữa tôi còn phải đi làm!”</p><p id=\"50\">“Được! Làm thịt hắn nhanh nào!”</p><p id=\"51\">“Tôi yểm trợ. Nhớ mà cảm tạ đấy!”</p><p id=\"52\">“Mong là rớt đồ hiếm!”</p><p id=\"53\">“Tấn công kết liễu bằng đội hình thật là ngầu chứ nhỉ? Đối thủ là Boss cuối cơ mà, không thể hiện cho hoành tráng thì mất mặt danh xưng top đầu lắm!”</p><p id=\"54\">Nở nụ cười sảng khoái không một chút sợ hãi, ma thuật tấn công với uy lực áp đảo ập đến như thủy triều, gần như lập tức giết chết Evil God.</p><p id=\"55\">Những người chơi hoang đường lấy đánh quái làm niềm vui, nện hàng loạt ma thuật siêu hung tàn đến quá đáng lên tà thần, Boss cuối vào giờ phút này trông lại có vẻ đáng thương.</p><p id=\"56\">Bị nhấn chìm trong vô số những vụ nổ, tà thần vừa phát ra tiếng kêu bi thương thảm thiết trước khi chết, vừa chậm rãi từ trên không trung rơi xuống như muốn đập tan mặt đất.</p><p id=\"57\">“Kết thúc… Không hổ danh Boss cuối, quá khó đối phó!”</p><p id=\"58\">“Giờ sao? Tôi không tham gia tiệc mừng đâu nhé, phải đi ngủ bây giờ...”</p><p id=\"59\">“Lát nữa tôi có việc nên cũng không tham gia. Tắt máy đây…”</p><p id=\"60\">“Tôi cũng vậy. Xin lỗi nhé, hẹn hôm khác bù...”</p><p id=\"61\">“Vậy hôm nay giải tán. Tôi đi làm đây, mọi người ngủ ngon!”</p><p id=\"62\">“ “ “ “ “Ngủ ngon!” ” ” ” ”</p><p id=\"63\">Từng người dịch chuyển ra ngoài và đã đăng xuất hết, chỉ còn có Satoshi ở lại lâu đài của Evil God, kiểm tra những chiến lợi phẩm mà họ đã nhận được.</p><p id=\"64\">Nhưng ông chú ở lại nơi này, trở thành khởi đầu của mọi chuyện.</p><p id=\"65\">Ông chú không hề phát hiện ra Evil God hơi hơi giật giật, còn đang bận xem chỉ số của bản thân, quan sát điểm kinh nghiệm tích lũy, suy nghĩ xem tiếp theo sẽ học Skill nào. Lúc này cơ thể Evil God đột nhiên cử động.</p><p id=\"66\">Trợn trừng mắt căm thù nhìn kẻ địch phía trước, chướng khí phun trào từ trong cơ thể.</p><p id=\"67\">“Ta không cho phép… không tha thứ… lũ sinh vật thấp kém… hủy diệt ta…”</p><p id=\"68\">“Cái quái…! HP bằng 0 rồi cơ mà…”</p><p id=\"69\">“Ta nguyền rủa! Lũ nữ thần kinh tởm phong ấn ta… Lũ ngu xuẩn vô tri chống lại ta… Ta sẽ mang tất cả các người theo cùng!”</p><p id=\"70\">“Event còn chưa kết thúc à? Không thể nào…?!”</p><p id=\"71\">Giải phóng tất cả căm hận, Evil God tung ra lời nguyền.</p><p id=\"72\">Ánh sáng đỏ đậm nhấn chìm tất cả.</p><p id=\"73\">Ngày hôm đó, hệ thống điện trên toàn Nhật Bản ngừng hoạt động.</p><p id=\"74\">Vài ngày sau, chính phủ phát hiện ra mười mấy người đã tử vong với nguyên nhân cái chết là “bí ẩn”.</p><p id=\"75\">Việc cấp bách lúc này là chữa trị hệ thống điện, những cái chết bị quên đi giữa vô vàn xôn xao hỗn loạn.</p><p id=\"76\">Xuất hiện lần cuối cùng ở một góc nhỏ trên báo, dần bị lãng quên theo thời gian.</p>\n")
                    .build();
            List<Chapter> chapters = new ArrayList<>();
            chapters.add(arafooChap);
            chapters.add(imotouChap);
            chapterRepository.saveAll(chapters);
        }
    }

    private void addVol() {
        if (volumeRepository.findAll().isEmpty()) {
            List<Volume> volumes = new ArrayList<>();
            Novel arafoo = novelRepository.findById("NOVEL-1").get();
            Novel imotou = novelRepository.findById("NOVEL-2").get();
            volumes.add(Volume.builder()
                    .title("One Shot").novel(arafoo)
                    .build());
            volumes.add(Volume.builder()
                    .title("One Shot").novel(imotou)
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
                    .extraNote("Ai đọc rồi hay chưa ta không cần biết, đọc convert hay đọc Eng hay vã được tiếng Trung tiếng Nhật gì ta cũng éo quan tâm, nhưng thích spoil tự lập project riêng ấy, éo ai quản!\n" +
                            "Chỗ của ta cấm spoil ok?\n" +
                            "\n" +
                            "==========\n" +
                            "\n" +
                            "Không nhận Donate dưới mọi hình thức\n" +
                            "\n" +
                            "Làm vì hứng thú\n" +
                            "\n" +
                            "30 ngày 1 chương hay là 3 ngày 1 chương cũng là tùy hứng\n" +
                            "\n" +
                            "==========\n" +
                            "\n" +
                            "Tác phẩm có các thuộc tính ẩn như sau: Loli, Big Boobs, Gender Bender, Harem, Oldman...\n" +
                            "\n" +
                            "Ông chú già khó tính solo dịch\n" +
                            "\n" +
                            "Già và chậm, không giục\n" +
                            "\n" +
                            "Edit chưa ngon sẽ không up, lỗi chính tả sẽ không up, đọc chưa mượt sẽ không up\n" +
                            "\n" +
                            "Muốn bomb thì phải nhịn vài ngày không chương nhá 21.gif\n" +
                            "\n" +
                            "Thích ăn vặt hàng ngày hay nhịn vài ngày chén 1 bữa ??? 21.gif\n" +
                            "\n" +
                            "Tiếp nhận và xem xét mọi ý kiến góp ý\n" +
                            "\n" +
                            "Thật sự khi đọc lại vẫn thấy gượng, mà để sát ý nhất nên không nghĩ được cách sửa\n" +
                            "\n" +
                            "Nguồn:\n" +
                            "\n" +
                            "WN: https://ncode.syosetu.com/n8515dc/\n" +
                            "\n" +
                            "LN: https://bszip.com/novel-52509.html\n" +
                            "\n" +
                            "Đặt mua ủng hộ tác giả tại đây http://mfbooks.jp/books/series/around/")
                    .translator(account)
                    .translationStatus(TranslationStatus.IN_PROCESS)
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
                    .name("Imouto sae Ireba Ii")
                    .otherName("A Sister's All You Need.")
                    .typeOfStory(TypeOfStory.TRANSLATION)
                    .summary("Truyện xoay quanh cuộc sống thường ngày của Itsuki Hashima, một tiểu thuyết gia cuồng em gái hạng nặng. Sở thích có phần 'điên khùng' cùng mong ước có một đứa em gái của cậu đã dẫn đến những tình huống hài hước, lãng mạn nhưng không kém phần sâu sắc xuyên suốt các tập truyện. ")
                    .extraNote("Trình độ tiếng Nhật của trans chỉ ở mức \"Arigathanks, Sorrymasen, Chotto minute\" nên truyện được dịch từ bản Eng.\n" +
                            "\n" +
                            "Nguồn Light Novel\n" +
                            "\n" +
                            "Đồng thời xin cảm ơn bạn Blank.428 đã lên màu những tấm hình minh họa vô cùng chất lượng.\n" +
                            "\n" +
                            "Chúc mọi người đọc truyện vui vẻ.")
                    .translationStatus(TranslationStatus.IN_PROCESS)
                    .author(author2)
                    .artist(artist2)
                    .types(types2)
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
            types.add(Type.builder().name("Action").build());
            types.add(Type.builder().name("Adapted to Manga").build());
            types.add(Type.builder().name("Adapted to Anime").build());
            types.add(Type.builder().name("Adapted to Drama CD").build());
            types.add(Type.builder().name("Adult").build());
            types.add(Type.builder().name("Adventure").build());
            types.add(Type.builder().name("Age Gap").build());
            types.add(Type.builder().name("Boys Love").build());
            types.add(Type.builder().name("Character Growth").build());
            types.add(Type.builder().name("Chinese Novel").build());
            types.add(Type.builder().name("Comedy").build());
            types.add(Type.builder().name("Cooking").build());
            types.add(Type.builder().name("Different Social Status").build());
            types.add(Type.builder().name("Drama").build());
            types.add(Type.builder().name("Ecchi").build());
            types.add(Type.builder().name("English Novel").build());
            types.add(Type.builder().name("Fantasy").build());
            types.add(Type.builder().name("Female Protagonist").build());
            types.add(Type.builder().name("Game").build());
            types.add(Type.builder().name("Gender Bender").build());
            types.add(Type.builder().name("Harem").build());
            types.add(Type.builder().name("Historical").build());
            types.add(Type.builder().name("Horror").build());
            types.add(Type.builder().name("Incest").build());
            types.add(Type.builder().name("Isekai").build());
            types.add(Type.builder().name("Josei").build());
            types.add(Type.builder().name("Korean Novel").build());
            types.add(Type.builder().name("Magic").build());
            types.add(Type.builder().name("Martial Arts").build());
            types.add(Type.builder().name("Mature").build());
            types.add(Type.builder().name("Mecha").build());
            types.add(Type.builder().name("Military").build());
            types.add(Type.builder().name("Misunderstanding").build());
            types.add(Type.builder().name("Mystery").build());
            types.add(Type.builder().name("Netorare").build());
            types.add(Type.builder().name("One shot").build());
            types.add(Type.builder().name("Otome Game").build());
            types.add(Type.builder().name("Psychological").build());
            types.add(Type.builder().name("Reverse Harem").build());
            types.add(Type.builder().name("Romance").build());
            types.add(Type.builder().name("School Life").build());
            types.add(Type.builder().name("Science Fiction").build());
            types.add(Type.builder().name("Seinen").build());
            types.add(Type.builder().name("Shoujo").build());
            types.add(Type.builder().name("Shoujo ai").build());
            types.add(Type.builder().name("Shounen").build());
            types.add(Type.builder().name("Shounen ai").build());
            types.add(Type.builder().name("Slice of Life").build());
            types.add(Type.builder().name("Slow Life").build());
            types.add(Type.builder().name("Sports").build());
            types.add(Type.builder().name("Super Power").build());
            types.add(Type.builder().name("Supernatural").build());
            types.add(Type.builder().name("Suspense").build());
            types.add(Type.builder().name("Tragedy").build());
            types.add(Type.builder().name("Web Novel").build());
            types.add(Type.builder().name("Wars").build());
            types.add(Type.builder().name("Workplace").build());
            types.add(Type.builder().name("Yuri").build());

            typeRepository.saveAll(types);
        }
    }

    private void addArtist() {
        if (artistRepository.findAll().isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            artists.add(Artist.builder().name("TEDDY").build());
            artists.add(Artist.builder().name("Nekoko").build());
            artists.add(Artist.builder().name("Chiri").build());
            artists.add(Artist.builder().name("Kantoku").build());
            artistRepository.saveAll(artists);
        }
    }

    private void addAuthor() {
        if (authorRepository.findAll().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            authors.add(Author.builder().name("Yomi Hirasaka").build());
            authors.add(Author.builder().name("Nekoko").build());
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
